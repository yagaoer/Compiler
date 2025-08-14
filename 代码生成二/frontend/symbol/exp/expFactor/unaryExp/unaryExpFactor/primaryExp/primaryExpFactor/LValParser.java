package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor;

import frontend.lexer.TokenCode;
import frontend.symbol.IdentParser;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.exp.Exp;
import frontend.symbol.Ident;
import frontend.symbol.exp.ExpParser;
import frontend.lexer.Token;

import java.util.ArrayList;

public class LValParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public LValParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public LVal parseLVal() {

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        Exp exp = null;
        Token rBrack = null;
        if (tokens.get(0).getCode().equals(TokenCode.LBRACK)) {

            lBrack = tokens.remove(0);

            exp = new ExpParser(tokens, symbolTable).parseExp();

            if (tokens.get(0).getCode().equals(TokenCode.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RBRACK);
            }
        }

        SymbolCode symbolCode = SymbolCode.VoidFunc;
        if (!cError(ident)) {
            symbolCode = this.symbolTable.getSymbol(ident.getToken().getName()).getSymbolCode();
            if (symbolCode.equals(SymbolCode.IntArray)) {
                if (lBrack != null) {
                    symbolCode = SymbolCode.Int;
                }
            } else if (symbolCode.equals(SymbolCode.ConstIntArray)) {
                if (lBrack != null) {
                    symbolCode = SymbolCode.ConstInt;
                }
            } else if (symbolCode.equals(SymbolCode.CharArray)) {
                if (lBrack != null) {
                    symbolCode = SymbolCode.Char;
                }
            } else if (symbolCode.equals(SymbolCode.ConstCharArray)) {
                if (lBrack != null) {
                    symbolCode = SymbolCode.ConstChar;
                }
            }
        }
        return new LVal(ident, lBrack, exp, rBrack, symbolCode, symbolTable);
    }

    private boolean cError(Ident ident) {
        if (this.symbolTable.cError(ident.getToken().getName())) {
            new Token("c", ident.getToken().getLine());
            return true;
        }
        return false;
    }
}
