package frontend;

import java.util.ArrayList;

public class CondParser {
    private ArrayList<Token> tokens;

    public CondParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Cond parseCond() {
        LOrExp lorExp = new LOrExpParser(tokens).parseLOrExp();
        return new Cond(lorExp);
    }
}
