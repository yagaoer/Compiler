package frontend;

import java.util.ArrayList;

public class StmtBreakParser {
    private ArrayList<Token> tokens;

    public StmtBreakParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtBreak parseStmtBreak() {

        Token breakTk = tokens.remove(0);

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new StmtBreak(breakTk, semicn);
    }
}
