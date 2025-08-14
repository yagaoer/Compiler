package frontend;

import java.util.ArrayList;

public class ConstExpParser {
    private ArrayList<Token> tokens;

    public ConstExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ConstExp parseConstExp() {
        AddExp addExp = new AddExpParser(tokens).parseAddExp();
        return new ConstExp(addExp);
    }
}
