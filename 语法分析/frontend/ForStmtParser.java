package frontend;

import java.util.ArrayList;

public class ForStmtParser {
    private ArrayList<Token> tokens;

    public ForStmtParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ForStmt parseForStmt() {

        LVal lVal = new LValParser(tokens).parseLVal();

        Token assign = tokens.remove(0);

        Exp exp = new ExpParser(tokens).parseExp();

        return new ForStmt(lVal, assign, exp);
    }
}
