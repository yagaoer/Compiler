package frontend.symbol.func.funcParam;

import frontend.lexer.*;
import frontend.symbol.BTypeParser;
import frontend.symbol.IdentParser;
import frontend.symbol.*;

import java.util.ArrayList;

public class FuncFParamParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;
    private ArrayList<Symbol> symbols;

    public FuncFParamParser(ArrayList<Token> tokens, SymbolTable symbolTable, ArrayList<Symbol> symbols) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
        this.symbols = symbols;
    }

    public FuncFParam parseFuncFParam() {

        BType bType = new BTypeParser(tokens).parseBtype();

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        Token rBrack = null;
        if (tokens.get(0).getCode().equals(TokenCode.LBRACK)) {

            lBrack = tokens.remove(0);


            if (tokens.get(0).getCode().equals(TokenCode.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RBRACK);
            }
        }
        addSymbol(bType, ident, lBrack, rBrack);
        return new FuncFParam(bType, ident, lBrack, rBrack, symbolTable);
    }

    public void addSymbol(BType bType, Ident ident, Token lBrack, Token rBrack) {
        SymbolCode symbolCode;
        int array;
        if (lBrack != null) {
            array = 1;
            if (bType.getToken().getCode().equals(TokenCode.CHARTK)) {
                symbolCode = SymbolCode.CharArray;
            } else {
                symbolCode = SymbolCode.IntArray;
            }
        } else {
            array = 0;
            if (bType.getToken().getCode().equals(TokenCode.CHARTK)) {
                symbolCode = SymbolCode.Char;
            } else {
                symbolCode = SymbolCode.Int;
            }
        }
        Symbol symbol = new Symbol(ident.getToken().getLine(), ident.getToken().getName(),
                symbolCode, array);
        symbol.setFParam(true);
        // b
        if (this.symbolTable.bError(ident.getToken().getName())) {
            new Token("b", ident.getToken().getLine());
        } else {
            this.symbolTable.addSymbol(symbol);
            this.symbols.add(symbol);
        }
    }
}
