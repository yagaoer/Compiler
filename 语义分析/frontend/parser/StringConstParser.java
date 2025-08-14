package frontend.parser;

import frontend.lexer.StringConst;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StringConstParser {
    private ArrayList<Token> tokens;

    public StringConstParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StringConst parseStringConst() {

        Token token = tokens.remove(0);

        return new StringConst(token);
    }
}
