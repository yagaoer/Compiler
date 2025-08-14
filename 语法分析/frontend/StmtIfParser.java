package frontend;

import java.util.ArrayList;

public class StmtIfParser {
    private ArrayList<Token> tokens;

    public StmtIfParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtIf parseStmtIf() {

        Token ifTk = tokens.remove(0);

        Token lParent = tokens.remove(0);

        Cond cond = new CondParser(tokens).parseCond();

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        Stmt stmt1 = new StmtParser(tokens).parseStmt();

        Token elseTk = null;
        Stmt stmt2 = null;
        if (tokens.get(0).getCode().equals(Code.ELSETK)) {

            elseTk = tokens.remove(0);

            stmt2 = new StmtParser(tokens).parseStmt();
        }

        return new StmtIf(ifTk, lParent, cond, rParent, stmt1, elseTk, stmt2);
    }
}
