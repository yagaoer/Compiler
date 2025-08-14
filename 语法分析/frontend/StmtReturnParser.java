package frontend;

import java.util.ArrayList;

public class StmtReturnParser {
    private ArrayList<Token> tokens;

    public StmtReturnParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtReturn parseStmtReturn() {

        Token returnTk = tokens.remove(0);

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

        return new StmtReturn(returnTk, exp, semicn);
    }
}
