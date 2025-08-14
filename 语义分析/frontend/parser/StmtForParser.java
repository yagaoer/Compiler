package frontend.parser;

import frontend.*;
import frontend.lexer.*;

import java.util.ArrayList;

public class StmtForParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtForParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtFor parseStmtFor() {

        Token forTk = tokens.remove(0);

        Token lParent = tokens.remove(0);

        ForStmt forStmt1 = null;
        if (tokens.get(0).getCode().equals(TokenCode.IDENFR)) {
            forStmt1 = new ForStmtParser(tokens, symbolTable).parseForStmt();
        }

        Token semicn1 = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn1 = tokens.remove(0);
        } else {
            semicn1 = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        Cond cond = null;
        if (tokens.get(0).getCode().equals(TokenCode.IDENFR) ||
                tokens.get(0).getCode().equals(TokenCode.INTCON) ||
                tokens.get(0).getCode().equals(TokenCode.CHRCON) ||
                tokens.get(0).getCode().equals(TokenCode.LPARENT) ||
                tokens.get(0).getCode().equals(TokenCode.PLUS) ||
                tokens.get(0).getCode().equals(TokenCode.MINU) ||
                tokens.get(0).getCode().equals(TokenCode.NOT)) {
            cond = new CondParser(tokens, symbolTable).parseCond();
        }

        Token semicn2 = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn2 = tokens.remove(0);
        } else {
            semicn2 = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        ForStmt forStmt2 = null;
        if (tokens.get(0).getCode().equals(TokenCode.IDENFR)) {
            forStmt2 = new ForStmtParser(tokens, symbolTable).parseForStmt();
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        SymbolTable symbolTable1 = new SymbolTable(symbolTable);
        symbolTable1.setInCycle(true);
        Stmt stmt = new StmtParser(tokens, symbolTable1).parseStmt();

        return new StmtFor(forTk, lParent, forStmt1, semicn1, cond, semicn2, forStmt2, rParent, stmt);
    }
}
