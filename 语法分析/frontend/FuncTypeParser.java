package frontend;

import java.util.ArrayList;

public class FuncTypeParser {
    private ArrayList<Token> tokens;

    public FuncTypeParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public FuncType parseFuncType() {

        Token token = tokens.remove(0);


        if (token.getCode().equals(Code.VOIDTK)) {
            return new FuncType(new FuncTypeVoid(token));
        }

        if (token.getCode().equals(Code.INTTK)) {
            return new FuncType(new FuncTypeInt(token));
        }

        return new FuncType(new FuncTypeChar(token));
    }
}
