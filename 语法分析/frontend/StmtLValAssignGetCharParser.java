package frontend;

import java.util.ArrayList;

public class StmtLValAssignGetCharParser {
    private ArrayList<Token> tokens;

    public StmtLValAssignGetCharParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtLValAssignGetChar parseStmtLValAssignGetChar() {
        LVal lval = new LValParser(tokens).parseLVal();

        Token assign = tokens.remove(0);

        Token getChar = tokens.remove(0);

        Token lParent = tokens.remove(0);

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new StmtLValAssignGetChar(lval, assign, getChar, lParent, rParent, semicn);
    }
}
