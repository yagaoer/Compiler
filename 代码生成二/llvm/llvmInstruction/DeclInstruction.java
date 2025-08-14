package llvm.llvmInstruction;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitVal;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitValArray;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitValFactor;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitVal;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitValArray;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitValFactor;
import frontend.symbol.exp.ConstExp;
import frontend.symbol.exp.Exp;
import frontend.symbol.decl.StringConst;
import llvm.Counter;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class DeclInstruction implements LLVMInstruction {
    private SymbolTable symbolTable;
    private Symbol symbol;
    private String name;
    private SymbolCode symbolCode;
    private int array; // 数组维数
    private ConstExp constExp = null; // 数组大小
    private ConstInitVal constInitVal = null; // 常量初值
    private VarInitVal varInitVal = null; // 变量初值
    private String iType = "i32"; // i32

    // Const(Array)
    public DeclInstruction(SymbolTable symbolTable, Symbol symbol, ConstExp constExp, ConstInitVal constInitVal) {
        this.symbolTable = symbolTable;
        this.symbol = symbol;
        this.name = symbol.getName();
        this.symbolCode = symbol.getSymbolCode();
        this.array = symbol.getArray();
        this.constExp = constExp;
        this.constInitVal = constInitVal;
    }

//    // Var
//    public LLVMGlobal(String name, SymbolCode symbolCode, int array, VarInitVal varInitVal) {
//        this.name = name;
//        this.symbolCode = symbolCode;
//        this.array = array;
//        this.varInitVal = varInitVal;
//    }

    // Var(Array)Init
    public DeclInstruction(SymbolTable symbolTable, Symbol symbol, ConstExp constExp, VarInitVal varInitVal) {
        this.symbolTable = symbolTable;
        this.symbol = symbol;
        this.name = symbol.getName();
        this.symbolCode = symbol.getSymbolCode();
        this.array = symbol.getArray();
        this.constExp = constExp;
        this.varInitVal = varInitVal;
    }

    // Var(Array)Null
    public DeclInstruction(SymbolTable symbolTable, Symbol symbol, ConstExp constExp) {
        this.symbolTable = symbolTable;
        this.symbol = symbol;
        this.name = symbol.getName();
        this.symbolCode = symbol.getSymbolCode();
        this.array = symbol.getArray();
        this.constExp = constExp;
    }

    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        if (symbolCode.name().contains("Char")) {
            this.iType = "i8";
        }
        if (constInitVal != null) {
            // 局部常量
            ConstInitValFactor constInitValFactor = constInitVal.getConstInitValFactor();
            if (constInitValFactor instanceof ConstExp) {
                // 常量值
                symbol.setLlvmType(iType + "*");
                String alloca = "   %" + this.name + " = alloca " + this.iType + "\n";
                strings.add(alloca);
                String res = ((ConstExp) constInitValFactor).toInstructions(cnt, iType, strings);
                if (Counter.getType(res).equals("i32") && iType.equals("i8")) {
                    String tmp = "%_" + cnt.use();
                    // %10 = trunc i32 %9 to i8
                    strings.add("   " + tmp + " = trunc i32 " + res + " to i8\n");
                    Counter.put(tmp, "i8");
                    res = tmp;
                }
                String store = "   store " + iType + " " + res
                        + ", " + iType + "*" + " %" + this.name + "\n";
                strings.add(store);
            } else if (constInitValFactor instanceof ConstInitValArray) {
                // 数组形式
                int num = this.constExp.toRes();
                symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                String alloca = "   %" + this.name + " = alloca " + "[" + num + " x " + this.iType + "]" + "\n";
                strings.add(alloca);
                String store = ((ConstInitValArray) constInitValFactor).localArrayInit(cnt, num, this.name, "[" + num + " x " + this.iType + "]", this.iType, strings);
                strings.add(store);
            } else {
                // 字符串常量
                int num = this.constExp.toRes();
                symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                String alloca = "   %" + this.name + " = alloca " + "[" + num + " x " + this.iType + "]" + "\n";
                strings.add(alloca);
                String store = ((StringConst) constInitValFactor).localArrayInit(cnt, num, this.name, "[" + num + " x " + this.iType + "]", this.iType);
                strings.add(store);
            }
        } else {
            // 局部变量
            if (this.varInitVal != null) {
                VarInitValFactor varInitValFactor = this.varInitVal.getVarInitValFactor();
                if (varInitValFactor instanceof Exp) {
                    // 变量值
                    symbol.setLlvmType(iType + "*");
                    String alloca = "   %" + this.name + " = alloca " + this.iType + "\n";
                    strings.add(alloca);
                    String res = ((Exp) varInitValFactor).toInstructions(cnt, iType, strings);
                    if (Counter.getType(res).equals("i32") && iType.equals("i8")) {
                        String tmp = "%_" + cnt.use();
                        // %10 = trunc i32 %9 to i8
                        strings.add("   " + tmp + " = trunc i32 " + res + " to i8\n");
                        Counter.put(tmp, "i8");
                        res = tmp;
                    }
                    String store = "   store " + iType + " " + res
                            + ", " + iType + "*" + " %" + this.name + "\n";
                    strings.add(store);
                } else if (varInitValFactor instanceof VarInitValArray) {
                    // 数组形式
                    int num = this.constExp.toRes();
                    symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                    String alloca = "   %" + this.name + " = alloca " + "[" + num + " x " + this.iType + "]" + "\n";
                    strings.add(alloca);
                    String store = ((VarInitValArray) varInitValFactor).localArrayInit(cnt, num, this.name, "[" + num + " x " + this.iType + "]", this.iType, strings);
                    strings.add(store);
                } else {
                    // 字符串常量
                    int num = this.constExp.toRes();
                    symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                    String alloca = "   %" + this.name + " = alloca " + "[" + num + " x " + this.iType + "]" + "\n";
                    strings.add(alloca);
                    String store = ((StringConst) varInitValFactor).localArrayInit(cnt, num, this.name, "[" + num + " x " + this.iType + "]", this.iType);
                    strings.add(store);
                }
            } else {
                if (array == 0) {
                    // 变量值0
                    symbol.setLlvmType(iType + "*");
                    String alloca = "   %" + this.name + " = alloca " + this.iType + "\n";
                    strings.add(alloca);
                    String store = "   store " + iType + " " + 0
                            + ", " + iType + "*" + " %" + this.name + "\n";
                    strings.add(store);
                } else {
                    // 数组形式
                    int num = this.constExp.toRes();
                    symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                    String alloca = "   %" + this.name + " = alloca " + "[" + num + " x " + this.iType + "]" + "\n";
                    strings.add(alloca);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < num; i++) {
                        String tmp = "%_" + cnt.use();
                        // %3 = getelementptr i32, i32* @a, i32 0
                        sb.append("   " + tmp + " = getelementptr " + "[" + num + " x " + this.iType + "]" + ", "
                                + "[" + num + " x " + this.iType + "]" + "* " + "%" + name + ", " + iType + " " + 0 +
                                ", " + iType + " " + i + "\n");
                        // store i32 1, i32* %2
                        sb.append("   store " + iType + " " + "0" + ", " + iType + "* " + tmp + "\n");
                    }
                    strings.add(sb.toString());
                }
            }
        }
        return strings;
    }
}
