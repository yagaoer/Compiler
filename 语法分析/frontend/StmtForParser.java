package frontend;

import java.util.ArrayList;

public class StmtForParser {
    private ArrayList<Token> tokens;

    public StmtForParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtFor parseStmtFor() {

        Token forTk = tokens.remove(0);

        Token lParent = tokens.remove(0);

        ForStmt forStmt1 = null;
        if (!tokens.get(0).getCode().equals(Code.SEMICN)) {
            forStmt1 = new ForStmtParser(tokens).parseForStmt();
        }

        Token semicn1 = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn1 = tokens.remove(0);
        } else {
            semicn1 = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        Cond cond = null;
        if (!tokens.get(0).getCode().equals(Code.SEMICN)) {
            cond = new CondParser(tokens).parseCond();
        }

        Token semicn2 = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn2 = tokens.remove(0);
        } else {
            semicn2 = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        ForStmt forStmt2 = null;
        if (!tokens.get(0).getCode().equals(Code.RPARENT)) {
            forStmt2 = new ForStmtParser(tokens).parseForStmt();
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        Stmt stmt = new StmtParser(tokens).parseStmt();

        return new StmtFor(forTk, lParent, forStmt1, semicn1, cond, semicn2, forStmt2, rParent, stmt);
    }
}
