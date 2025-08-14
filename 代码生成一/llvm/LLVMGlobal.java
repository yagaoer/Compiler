package llvm;

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

import java.util.ArrayList;

public class LLVMGlobal {
    private SymbolTable symbolTable;
    private Symbol symbol;
    private String name;
    private SymbolCode symbolCode;
    private int array; // 数组维数
    private ConstExp constExp = null; // 数组大小
    private ConstInitVal constInitVal = null; // 常量初值
    private VarInitVal varInitVal = null; // 变量初值
    private ArrayList<Symbol> funcRParams = null; // 函数参数
    private String iType = "i32"; // i32

//    // Const
//    public LLVMGlobal(String name, SymbolCode symbolCode,
//                  Integer array, ConstInitVal constInitVal) {
//        this.name = name;
//        this.symbolCode = symbolCode;
//        this.array = array;
//        this.constInitVal = constInitVal;
//    }

    // Const(Array)
    public LLVMGlobal(SymbolTable symbolTable, Symbol symbol, ConstExp constExp, ConstInitVal constInitVal) {
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
    public LLVMGlobal(SymbolTable symbolTable, Symbol symbol, ConstExp constExp, VarInitVal varInitVal) {
        this.symbolTable = symbolTable;
        this.symbol = symbol;
        this.name = symbol.getName();
        this.symbolCode = symbol.getSymbolCode();
        this.array = symbol.getArray();
        this.constExp = constExp;
        this.varInitVal = varInitVal;
    }

    // Var(Array)Null
    public LLVMGlobal(SymbolTable symbolTable, Symbol symbol, ConstExp constExp) {
        this.symbolTable = symbolTable;
        this.symbol = symbol;
        this.name = symbol.getName();
        this.symbolCode = symbol.getSymbolCode();
        this.array = symbol.getArray();
        this.constExp = constExp;
    }

    public ArrayList<String> getString() {
        ArrayList<String> strings = new ArrayList<>();
        if (symbolCode.name().contains("Char")) {
            this.iType = "i8";
        }
        if (constInitVal != null) {
            // 全局常量
            ConstInitValFactor constInitValFactor = constInitVal.getConstInitValFactor();
            if (constInitValFactor instanceof ConstExp) {
                // 常量值
                symbol.setLlvmType(this.iType + "*");
                String string = "@" + this.name + " = dso_local global "
                        + this.iType + " " + ((ConstExp) constInitValFactor).toRes()
                        + "\n";
                strings.add(string);
            } else if (constInitValFactor instanceof ConstInitValArray) {
                // 数组形式
                int num = this.constExp.toRes();
                symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                String string = "@" + this.name + " = dso_local global "
                        + "[" + num + " x " + this.iType + "]" + " " +
                        ((ConstInitValArray) constInitValFactor).globalArrayInit(num, this.iType)
                        + "\n";
                strings.add(string);
            } else {
                // 字符串常量
                int num = this.constExp.toRes();
                symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                String string = "@" + this.name + " = dso_local global "
                        + "[" + num + " x " + this.iType + "]" + " " +
                        ((StringConst) constInitValFactor).llvmArrayInit(num, this.iType)
                        + "\n";
                strings.add(string);
            }
        } else {
            // 全局变量
            if (this.varInitVal != null) {
                VarInitValFactor varInitValFactor = this.varInitVal.getVarInitValFactor();
                if (varInitValFactor instanceof Exp) {
                    // 常量值
                    symbol.setLlvmType(this.iType + "*");
                    String string = "@" + this.name + " = dso_local global "
                            + this.iType + " " + ((Exp) varInitValFactor).toRes()
                            + "\n";
                    strings.add(string);
                } else if (varInitValFactor instanceof VarInitValArray) {
                    // 数组形式
                    int num = this.constExp.toRes();
                    symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                    String string = "@" + this.name + " = dso_local global "
                            + "[" + num + " x " + this.iType + "]" + " " +
                            ((VarInitValArray) varInitValFactor).llvmArrayInit(num, this.iType)
                            + "\n";
                    strings.add(string);
                } else {
                    // 字符串常量
                    int num = this.constExp.toRes();
                    symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                    String string = "@" + this.name + " = dso_local global "
                            + "[" + num + " x " + this.iType + "]" + " " +
                            ((StringConst) varInitValFactor).llvmArrayInit(num, this.iType)
                            + "\n";
                    strings.add(string);
                }
            } else {
                if (array == 0) {
                    // 变量值0
                    symbol.setLlvmType(this.iType + "*");
                    String string = "@" + this.name + " = dso_local global "
                            + this.iType + " " + 0
                            + "\n";
                    strings.add(string);
                } else {
                    // 数组形式
                    int num = this.constExp.toRes();
                    symbol.setLlvmType("[" + num + " x " + this.iType + "]*");
                    String string = "@" + this.name + " = dso_local global "
                            + "[" + num + " x " + this.iType + "]" + " " + "zeroinitializer"
                            + "\n";
                    strings.add(string);
                }
            }
        }
        return strings;
    }
}
