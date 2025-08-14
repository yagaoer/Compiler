package frontend;

import java.util.ArrayList;

public class PrimaryExpExpParser {
    private ArrayList<Token> tokens;

    public PrimaryExpExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public PrimaryExpExp parsePrimaryExpExp() {

        Token lParent = tokens.remove(0);

        Exp exp = new ExpParser(tokens).parseExp();

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        return new PrimaryExpExp(lParent, exp, rParent);
    }
}
