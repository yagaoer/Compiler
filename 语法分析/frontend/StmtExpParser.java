package frontend;

import java.util.ArrayList;

public class StmtExpParser {
    private ArrayList<Token> tokens;

    public StmtExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtExp parseStmtExp() {

        Exp exp = null;
        if (!tokens.get(0).getCode().equals(Code.SEMICN)) {
            exp = new ExpParser(tokens).parseExp();
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new StmtExp(exp, semicn);
    }
}
