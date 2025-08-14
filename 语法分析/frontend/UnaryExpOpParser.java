package frontend;

import java.util.ArrayList;

public class UnaryExpOpParser {
    private ArrayList<Token> tokens;

    public UnaryExpOpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public UnaryExpOp parseUnaryExpOp() {

        UnaryOp unaryOp = new UnaryOpParser(tokens).parseUnaryOp();

        UnaryExp unaryExp = new UnaryExpParser(tokens).parseUnaryExp();

        return new UnaryExpOp(unaryOp, unaryExp);
    }
}
