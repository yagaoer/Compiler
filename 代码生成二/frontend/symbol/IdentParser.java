package frontend.symbol;

import frontend.symbol.Ident;
import frontend.lexer.Token;

import java.util.ArrayList;

public class IdentParser {
    private ArrayList<Token> tokens;

    public IdentParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Ident parseIdent() {
        Token token = tokens.remove(0);
        return new Ident(token);
    }
}
