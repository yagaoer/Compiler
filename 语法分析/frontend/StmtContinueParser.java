package frontend;

import java.util.ArrayList;

public class StmtContinueParser {
    private ArrayList<Token> tokens;

    public StmtContinueParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtContinue parseStmtContinue() {

        Token continueTk = tokens.remove(0);

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new StmtContinue(continueTk, semicn);
    }
}
