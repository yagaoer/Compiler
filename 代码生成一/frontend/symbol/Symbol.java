package frontend.symbol;

import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitVal;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitVal;

import java.util.ArrayList;
import java.util.HashMap;

public class Symbol {
    private int line;
    public String name;
    private SymbolCode symbolCode;
    private int array; // 数组维数
    private ConstInitVal constInitVal = null; // 常量初值
    private VarInitVal varInitVal = null; // 变量初值
    private ArrayList<Symbol> funcFParams = null; // 函数参数
    private String llvmType = null;
    private HashMap<Symbol, String> params;

    private boolean isGlobal = false;

    private boolean isFParam = false;

    // Const
    public Symbol(Integer line, String name, SymbolCode symbolCode,
                  Integer array, ConstInitVal constInitVal) {
        this.line = line;
        this.name = name;
        this.symbolCode = symbolCode;
        this.array = array;
        this.constInitVal = constInitVal;
    }

    // Var
    public Symbol(int line, String name, SymbolCode symbolCode, int array, VarInitVal varInitVal) {
        this.line = line;
        this.name = name;
        this.symbolCode = symbolCode;
        this.array = array;
        this.varInitVal = varInitVal;
    }

    // Func
    public Symbol(int line, String name, SymbolCode symbolCode, ArrayList<Symbol> funcFParams) {
        this.line = line;
        this.name = name;
        this.symbolCode = symbolCode;
        this.funcFParams = funcFParams;
    }

    // FuncFPram
    public Symbol(int line, String name, SymbolCode symbolCode, int array) {
        this.line = line;
        this.name = name;
        this.symbolCode = symbolCode;
        this.array = array;
    }

    public int getArray() {
        return array;
    }

    public ArrayList<Symbol> getFuncFParams() {
        return funcFParams;
    }

    public String getName() {
        return name + this.line;
    }

    public int getLine() {
        return line;
    }

    public SymbolCode getSymbolCode() {
        return symbolCode;
    }

    public ConstInitVal getConstInitVal() {
        return constInitVal;
    }

    public VarInitVal getVarInitVal() {
        return varInitVal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public void setFParam(boolean FParam) {
        isFParam = FParam;
    }

    public boolean isFParam() {
        return isFParam;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setLlvmType(String llvmType) {
        this.llvmType = llvmType;
    }

    public String getLlvmType() {
        return llvmType;
    }

    public void setParams(HashMap<Symbol, String> params) {
        this.params = params;
    }

    public HashMap<Symbol, String> getParams() {
        return params;
    }
}
