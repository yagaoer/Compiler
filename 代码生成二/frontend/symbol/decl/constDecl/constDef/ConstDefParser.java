package frontend.symbol.decl.constDecl.constDef;

import frontend.lexer.*;
import frontend.symbol.exp.ConstExpParser;
import frontend.symbol.IdentParser;
import frontend.symbol.*;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitValParser;
import frontend.symbol.exp.ConstExp;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitVal;

import java.util.ArrayList;

public class ConstDefParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;
    private BType bType;
    public ConstDefParser(ArrayList<Token> tokens, SymbolTable symbolTable, BType btype) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
        this.bType = btype;
    }

    public ConstDef parseConstDef() {

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        ConstExp constExp = null;
        Token rBrack = null;
        if (tokens.get(0).getCode().equals(TokenCode.LBRACK)) {

            lBrack = tokens.remove(0);

            constExp = new ConstExpParser(tokens, symbolTable).parseConstExp();

            if (tokens.get(0).getCode().equals(TokenCode.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RBRACK);
            }
        }

        Token assign = tokens.remove(0);

        ConstInitVal constInitval = new ConstInitValParser(tokens, symbolTable).parseConstInitVal();

        addSymbol(ident, lBrack, constExp, rBrack, assign, constInitval);

        return new ConstDef(ident, lBrack, constExp, rBrack, assign, constInitval, symbolTable);
    }

    private void addSymbol(Ident ident, Token lBrack, ConstExp constExp,
                           Token rBrack, Token assign, ConstInitVal constInitval) {
        SymbolCode symbolCode;
        int array;
        if (lBrack != null) {
            array = 1;
            if (bType.getToken().getCode().equals(TokenCode.CHARTK)) {
                symbolCode = SymbolCode.ConstCharArray;
            } else {
                symbolCode = SymbolCode.ConstIntArray;
            }
        } else {
            array = 0;
            if (bType.getToken().getCode().equals(TokenCode.CHARTK)) {
                symbolCode = SymbolCode.ConstChar;
            } else {
                symbolCode = SymbolCode.ConstInt;
            }
        }
        Symbol symbol = new Symbol(ident.getToken().getLine(), ident.getToken().getName(),
                symbolCode, array, constInitval);
        symbol.setGlobal(this.symbolTable.isGlobal());
        // bError
        if (this.symbolTable.bError(ident.getToken().getName())) {
            new Token("b", ident.getToken().getLine());
        } else {
            this.symbolTable.addSymbol(symbol);
        }
    }
}
