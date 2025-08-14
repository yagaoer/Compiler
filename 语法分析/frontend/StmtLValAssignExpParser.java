package frontend;

import java.util.ArrayList;

public class StmtLValAssignExpParser {
    private ArrayList<Token> tokens;

    public StmtLValAssignExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtLValAssignExp parseStmtLValAssignExp() {

        LVal lval = new LValParser(tokens).parseLVal();

        Token assign = tokens.remove(0);

        Exp exp = new ExpParser(tokens).parseExp();

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new StmtLValAssignExp(lval, assign, exp, semicn);
    }
}
