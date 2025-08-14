package frontend;

import java.util.ArrayList;

public class UnaryExpParser {
    private ArrayList<Token> tokens;

    public UnaryExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public UnaryExp parseUnaryExp() {

        if (tokens.get(0).getCode().equals(Code.IDENFR) &&
                tokens.get(1).getCode().equals(Code.LPARENT)) {
            return new UnaryExp(new UnaryExpFuncRParser(tokens).parseUnaryFuncRExp());
        }

        if (tokens.get(0).getCode().equals(Code.PLUS) ||
                tokens.get(0).getCode().equals(Code.MINU) ||
                tokens.get(0).getCode().equals(Code.NOT)) {
            return new UnaryExp(new UnaryExpOpParser(tokens).parseUnaryExpOp());
        }

        return new UnaryExp(new PrimaryExpParser(tokens).parsePrimaryExp());
    }
}
