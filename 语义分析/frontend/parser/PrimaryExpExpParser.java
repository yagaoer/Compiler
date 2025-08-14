package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.Exp;
import frontend.lexer.PrimaryExpExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class PrimaryExpExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public PrimaryExpExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public PrimaryExpExp parsePrimaryExpExp() {

        Token lParent = tokens.remove(0);

        Exp exp = new ExpParser(tokens, symbolTable).parseExp();

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        return new PrimaryExpExp(lParent, exp, rParent);
    }
}
