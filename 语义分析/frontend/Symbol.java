package frontend;

import frontend.lexer.ConstInitVal;
import frontend.lexer.VarInitVal;

import java.util.ArrayList;

public class Symbol {
    private int line;
    private String name;
    private SymbolCode symbolCode;
    private int array; // 数组维数
    private ConstInitVal constInitVal = null; // 常量初值
    private VarInitVal varInitVal = null; // 变量初值
    private ArrayList<Symbol> funcRParams = null; // 函数参数

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
    public Symbol(int line, String name, SymbolCode symbolCode, ArrayList<Symbol> funcRParams) {
        this.line = line;
        this.name = name;
        this.symbolCode = symbolCode;
        this.funcRParams = funcRParams;
    }

    // FuncFPram
    public Symbol(int line, String name, SymbolCode symbolCode, int array) {
        this.line = line;
        this.name = name;
        this.symbolCode = symbolCode;
        this.array = array;
    }

    public ArrayList<Symbol> getFuncRParams() {
        return funcRParams;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public SymbolCode getSymbolCode() {
        return symbolCode;
    }
}
