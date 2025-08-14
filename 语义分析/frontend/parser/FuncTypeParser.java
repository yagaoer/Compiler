package frontend.parser;

import frontend.*;
import frontend.lexer.*;

import java.util.ArrayList;

public class FuncTypeParser {
    private ArrayList<Token> tokens;

    public FuncTypeParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public FuncType parseFuncType() {

        Token token = tokens.remove(0);


        if (token.getCode().equals(TokenCode.VOIDTK)) {
            return new FuncType(new FuncTypeVoid(token));
        }

        if (token.getCode().equals(TokenCode.INTTK)) {
            return new FuncType(new FuncTypeInt(token));
        }

        return new FuncType(new FuncTypeChar(token));
    }
}
