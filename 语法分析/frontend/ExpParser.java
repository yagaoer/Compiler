package frontend;

import java.util.ArrayList;

public class ExpParser {
    private ArrayList<Token> tokens;

    public ExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Exp parseExp() {
        AddExp addExp = new AddExpParser(tokens).parseAddExp();
        return new Exp(addExp);
    }
}
