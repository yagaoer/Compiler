package frontend.symbol.func.funcParam;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.Ident;
import frontend.lexer.Token;
import frontend.symbol.BType;
import llvm.Counter;

import java.util.HashMap;

public class FuncFParam {
    private final String name = "<FuncFParam>";
    private BType btype; // BType
    private Ident ident; // Ident
    private Token lBrack;
    private Token rBrack; // ['[' ']']
    private SymbolTable symbolTable;

    public FuncFParam(BType btype, Ident ident, Token lBrack, Token rBrack, SymbolTable symbolTable) {
        this.btype = btype;
        this.ident = ident;
        this.lBrack = lBrack;
        this.rBrack = rBrack;
        this.symbolTable = symbolTable;
    }
//
//    public void parseLLVMParm(ArrayList<Symbol> symbols) {
//        SymbolCode symbolCode;
//        int array;
//        if (this.lBrack != null) {
//            array = 1;
//            if (this.btype.getToken().getCode().equals(TokenCode.CHARTK)) {
//                symbolCode = SymbolCode.CharArray;
//            } else {
//                symbolCode = SymbolCode.IntArray;
//            }
//        } else {
//            array = 0;
//            if (btype.getToken().getCode().equals(TokenCode.CHARTK)) {
//                symbolCode = SymbolCode.Char;
//            } else {
//                symbolCode = SymbolCode.Int;
//            }
//        }
//        Symbol symbol = new Symbol(ident.getToken().getLine(), ident.getToken().getName(),
//                symbolCode, array);
//
//        symbolTable.addSymbol(symbol);
//        symbols.add(symbol);
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.btype.toString()).append(this.ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.rBrack.toString());
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }

    public String getString(Counter cnt, HashMap<Symbol, String> params) {
        // i32 %0, i32* %1
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        String res = "%_" + cnt.use();
        params.put(symbol, res);
        switch (symbol.getSymbolCode()) {
            case Char -> {
                symbol.setLlvmType("i8*");
                Counter.put(res, "i8");
            }
            case Int -> {
                symbol.setLlvmType("i32*");
                Counter.put(res, "i32");
            }
            case CharArray -> {
                symbol.setLlvmType("i8**");
                Counter.put(res, "i8*");
            }
            case IntArray -> {
                symbol.setLlvmType("i32**");
                Counter.put(res, "i32*");
            }
        }
        return Counter.getType(res) + " " + res;
    }

//    public void toInstructions(Integer cnt, String iType, ArrayList<String> strings) {
//        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
//        if (symbol.getArray() == 1) {
//
//        } else {
//            String alloca = "   %" + symbol.getName() + " = alloca " + iType + "\n";
//            String store = "   store " + iType + " " + "%" + symbol.getName()
//                    + ", " + iType + "*" + " %" + this.name + "\n";
//        }
//    }
}
