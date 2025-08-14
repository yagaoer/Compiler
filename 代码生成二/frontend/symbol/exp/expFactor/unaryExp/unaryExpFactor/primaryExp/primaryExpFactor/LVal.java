package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.Ident;
import frontend.lexer.Token;
import frontend.symbol.exp.ConstExp;
import frontend.symbol.exp.Exp;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitValArray;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitValFactor;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitValArray;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitValFactor;
import frontend.symbol.decl.StringConst;
import llvm.Counter;

import java.util.ArrayList;

public class LVal implements PrimaryExpFactor {
    /*
     Ident ['[' Exp ']']
     */
    private final String name = "<LVal>";
    private Ident ident; // Ident
    private Token lBrack; // '['
    private Exp exp; // Exp
    private Token rBrack; // ']'
    private SymbolCode symbolCode;
    private SymbolTable symbolTable;

    public LVal(Ident ident, Token lBrack, Exp exp, Token rBrack, SymbolCode symbolCode, SymbolTable symbolTable) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.exp = exp;
        this.rBrack = rBrack;
        this.symbolCode = symbolCode;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (this.lBrack != null) {
            sb.append(lBrack.toString()).append(exp.toString()).append(rBrack.toString());
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return symbolCode;
    }

    @Override
    public int toRes() {
        Symbol symbol = symbolTable.getSymbolCheck(this.ident.getToken().getName());
        if (lBrack == null) {
            // 值
            if (symbol.getConstInitVal() != null) {
                ConstInitValFactor constInitValFactor = symbol.getConstInitVal().getConstInitValFactor();
                return ((ConstExp) constInitValFactor).toRes();
            } else {
                if (symbol.getVarInitVal() != null) {
                    VarInitValFactor varInitValFactor = symbol.getVarInitVal().getVarInitValFactor();
                    return ((Exp) varInitValFactor).toRes();
                }
                return 0;
            }
        } else {
            // 数组
            if (symbol.getConstInitVal() != null) {
                ConstInitValFactor constInitValFactor = symbol.getConstInitVal().getConstInitValFactor();
                int index = this.exp.toRes();
                if (constInitValFactor instanceof ConstInitValArray) {
                    return ((ConstInitValArray) constInitValFactor).toRes(index);
                } else {
                    return ((StringConst) constInitValFactor).toRes(index);
                }
            } else {
                if (symbol.getVarInitVal() != null) {
                    VarInitValFactor varInitValFactor = symbol.getVarInitVal().getVarInitValFactor();
                    int index = this.exp.toRes();
                    if (varInitValFactor instanceof VarInitValArray) {
                        return ((VarInitValArray) varInitValFactor).toRes(index);
                    } else {
                        return ((StringConst) varInitValFactor).toRes(index);
                    }
                }
                return 0;
            }
        }
    }

    @Override
    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        Symbol symbol = symbolTable.getSymbolCheck(this.ident.getToken().getName());
        String symbolName;
        if (symbol.isGlobal()) {
            symbolName = "@" + symbol.getName();
        } else {
            symbolName = "%" + symbol.getName();
        }
        iType = "i32";
        if (symbol.getLlvmType().contains("i8")) {
            iType = "i8";
        }
        String arrayType = symbol.getLlvmType().substring(0, symbol.getLlvmType().length() - 1);
        if (lBrack == null && symbol.getArray() == 0) {
            // ident
//            System.out.print("LVal " + cnt + " ");
            String res = "%_" + cnt.use();
            if (symbol.getLlvmType().contains("[")) {
                // %3 = getelementptr i32, i32* @a, i32 0
                strings.add("   " + res + " = getelementptr " + arrayType + ", "
                        + arrayType + "* " + symbolName + ", " + "i32 0" + ", " + "i32 " + 0 + "\n");
                Counter.put(res, iType + "*");
                return res;
            } else {
//                System.out.print(cnt + "\n");
                strings.add("   " + res + " = load " + iType + ", " + iType + "* " + symbolName + "\n");
                String tmp;
                if (iType.equals("i8")) {
                    tmp = "%_" + cnt.use();
                    // %8 = zext i8 %7 to i32
                    strings.add("   " + tmp + " = zext " + iType + " " + res + " to " + "i32" + "\n");
                } else {
                    tmp = res;
                }
                Counter.put(tmp, "i32");
                return tmp;
            }
        } else if (lBrack == null) {
            String ptrPtr = symbolName;
            if (symbol.isFParam()) {
                ptrPtr = "%_" + cnt.use();
                strings.add("   " + ptrPtr + " = load " + iType + "*, " + iType + "** " + symbolName + "\n");
            }
            String ptr = "%_" + cnt.use();
            if (symbol.getLlvmType().contains("[")) {
                // %3 = getelementptr i32, i32* @a, i32 0
                strings.add("   " + ptr + " = getelementptr " + arrayType + ", "
                        + arrayType + "* " + ptrPtr + ", " + "i32 0" + ", " + "i32 " + 0 + "\n");
            } else {
                // %3 = getelementptr i32, i32* @a, i32 0
                strings.add("   " + ptr + " = getelementptr " + iType + ", "
                        + iType + "* " + ptrPtr + ", " + "i32 " + 0 + "\n");
            }
            Counter.put(ptr, iType + "*");
            return ptr;
        } else {
            // ident[exp]
            String index = this.exp.toInstructions(cnt, iType, strings);
            String ptrPtr = symbolName;
            if (symbol.isFParam()) {
                ptrPtr = "%_" + cnt.use();
                strings.add("   " + ptrPtr + " = load " + iType + "*, " + iType + "** " + symbolName + "\n");
            }
            String ptr = "%_" + cnt.use();
            if (symbol.getLlvmType().contains("[")) {
                // %3 = getelementptr i32, i32* @a, i32 0
                strings.add("   " + ptr + " = getelementptr " + arrayType + ", "
                        + arrayType + "* " + ptrPtr + ", " + "i32 0" + ", " + "i32 " + index + "\n");
            } else {
                // %3 = getelementptr i32, i32* @a, i32 0
                strings.add("   " + ptr + " = getelementptr " + iType + ", "
                        + iType + "* " + ptrPtr + ", " + "i32 " + index + "\n");
            }
            // %7 = load i32, i32* %1
            String res = "%_" + cnt.use();
            strings.add("   " + res + " = load " + iType + ", " + iType + "* " + ptr + "\n");
            String tmp;
            if (iType.equals("i8")) {
                tmp = "%_" + cnt.use();
                // %8 = zext i8 %7 to i32
                strings.add("   " + tmp + " = zext " + iType + " " + res + " to " + "i32" + "\n");
            } else {
                tmp = res;
            }
            Counter.put(tmp, "i32");
            return tmp;
        }
    }

    public ArrayList<String> toPtrInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        ArrayList<String> ret = new ArrayList<>();
        Symbol symbol = symbolTable.getSymbolCheck(this.ident.getToken().getName());
        String symbolName;
        if (symbol.isGlobal()) {
            symbolName = "@" + symbol.getName();
        } else {
            symbolName = "%" + symbol.getName();
        }
        iType = "i32";
        if (symbol.getLlvmType().contains("i8")) {
            iType = "i8";
        }
        String arrayType = symbol.getLlvmType().substring(0, symbol.getLlvmType().length() - 1);
        if (lBrack == null) {
            // 值
            ret.add(symbol.getLlvmType());
            ret.add(symbolName);
            return ret;
        } else {
            // 数组
            String index = this.exp.toInstructions(cnt, iType, strings);
            String ptrPtr = symbolName;
            if (symbol.isFParam()) {
                ptrPtr = "%_" + cnt.use();
                strings.add("   " + ptrPtr + " = load " + iType + "*, " + iType + "** " + symbolName + "\n");
            }
            String ptr = "%_" + cnt.use();
            if (symbol.getLlvmType().contains("[")) {
                // %3 = getelementptr i32, i32* @a, i32 0
                strings.add("   " + ptr + " = getelementptr " + arrayType + ", "
                        + arrayType + "* " + ptrPtr + ", " + "i32 0" + ", " + "i32 " + index + "\n");
            } else {
                // %3 = getelementptr i32, i32* @a, i32 0
                strings.add("   " + ptr + " = getelementptr " + iType + ", "
                        + iType + "* " + ptrPtr + ", " +  "i32 " + index + "\n");
            }
            Counter.put(ptr, iType + "*");
            ret.add(Counter.getType(ptr));
            ret.add(ptr);
            return ret;
        }
    }

    public Ident getIdent() {
        return ident;
    }
}
