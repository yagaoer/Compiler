package frontend.symbol;

import frontend.symbol.BType;
import frontend.lexer.Token;

import java.util.ArrayList;

public class BTypeParser {
    private ArrayList<Token> tokens;

    public BTypeParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public BType parseBtype() {

        BType btype = new BType(tokens.remove(0));

        return btype;
    }
}
