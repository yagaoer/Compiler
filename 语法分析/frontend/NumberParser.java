package frontend;

import java.util.ArrayList;

public class NumberParser {
    private ArrayList<Token> tokens;
    public NumberParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Number parseNumber() {

        Token intConst = tokens.remove(0);

        return new Number(intConst);
    }
}
