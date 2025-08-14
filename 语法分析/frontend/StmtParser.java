package frontend;

import java.util.ArrayList;

public class StmtParser {

    private ArrayList<Token> tokens;

    public StmtParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Stmt parseStmt() {
        if (tokens.get(0).getCode().equals(Code.IFTK)) {
            return new Stmt(new StmtIfParser(tokens).parseStmtIf());
        }
        if (tokens.get(0).getCode().equals(Code.FORTK)) {
            return new Stmt(new StmtForParser(tokens).parseStmtFor());
        }
        if (tokens.get(0).getCode().equals(Code.BREAKTK)) {
            return new Stmt(new StmtBreakParser(tokens).parseStmtBreak());
        }
        if (tokens.get(0).getCode().equals(Code.CONTINUETK)) {
            return new Stmt(new StmtContinueParser(tokens).parseStmtContinue());
        }
        if (tokens.get(0).getCode().equals(Code.RETURNTK)) {
            return new Stmt(new StmtReturnParser(tokens).parseStmtReturn());
        }
        if (tokens.get(0).getCode().equals(Code.PRINTFTK)) {
            return new Stmt(new StmtPrintfParser(tokens).parseStmtPrintf());
        }
        if (tokens.get(0).getCode().equals(Code.LBRACE)) {
            return new Stmt(new StmtBlock(new BlockParser(tokens).parseBlock()));
        }
        if (tokens.get(0).getCode().equals(Code.LPARENT) || tokens.get(0).getCode().equals(Code.INTCON) ||
                tokens.get(0).getCode().equals(Code.PLUS) || tokens.get(0).getCode().equals(Code. MINU)) {
            return new Stmt(new StmtExpParser(tokens).parseStmtExp());
        }
        int i = 0;
        while (!tokens.get(i).getCode().equals(Code.SEMICN)) {
            if (tokens.get(i).getCode().equals(Code.ASSIGN)) {
                if (tokens.get(i + 1).getCode().equals(Code.GETINTTK)) {
                    return new Stmt(new StmtLValAssignGetIntParser(tokens).parseStmtLValAssignGetInt());
                }
                if (tokens.get(i + 1).getCode().equals(Code.GETCHARTK)) {
                    return new Stmt(new StmtLValAssignGetCharParser(tokens).parseStmtLValAssignGetChar());
                }
                return new Stmt(new StmtLValAssignExpParser(tokens).parseStmtLValAssignExp());
            }
            i++;
        }
        return new Stmt(new StmtExpParser(tokens).parseStmtExp());
    }
}
