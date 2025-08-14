package frontend;

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
