package frontend.parser;

import frontend.*;
import frontend.lexer.*;

import java.util.ArrayList;

public class VarDefParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;
    private BType bType;

    public VarDefParser(ArrayList<Token> tokens, SymbolTable symbolTable, BType btype) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
        this.bType = btype;
    }

    public VarDef parseVarDef() {

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        ConstExp constExp = null;
        Token rBrack = null;
        while (tokens.get(0).getCode().equals(TokenCode.LBRACK)) {

            lBrack = tokens.remove(0);

            constExp = new ConstExpParser(tokens, symbolTable).parseConstExp();

            if (tokens.get(0).getCode().equals(TokenCode.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RBRACK);
            }
        }

        VarDefFactor varDefFactor;
        VarInitVal varInitVal = null;
        if (tokens.get(0).getCode().equals(TokenCode.ASSIGN)) {

            Token assign = tokens.remove(0);

            varInitVal = new VarInitValParser(tokens, symbolTable).parseInitVal();

           varDefFactor = new VarDefWithAssign(ident, lBrack, constExp, rBrack, assign, varInitVal);
        } else {
            varDefFactor = new VarDefWithNull(ident, lBrack, constExp, rBrack);
        }

        addSymbol(ident, lBrack, constExp, rBrack, varInitVal);
        return new VarDef(varDefFactor);
    }

    private void addSymbol(Ident ident, Token lBrack, ConstExp constExp,
                           Token rBrack, VarInitVal varInitVal) {
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
                symbolCode, array, varInitVal);
        // bError
        if (this.symbolTable.bError(ident.getToken().getName())) {
            new Token("b", ident.getToken().getLine());
        } else {
            this.symbolTable.addSymbol(symbol);
        }
    }
}
