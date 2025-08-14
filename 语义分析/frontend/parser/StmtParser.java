package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.Stmt;
import frontend.lexer.StmtBlock;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StmtParser {

    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public Stmt parseStmt() {
        if (tokens.get(0).getCode().equals(TokenCode.IFTK)) {
            return new Stmt(new StmtIfParser(tokens, symbolTable).parseStmtIf());
        }
        if (tokens.get(0).getCode().equals(TokenCode.FORTK)) {
            return new Stmt(new StmtForParser(tokens, symbolTable).parseStmtFor());
        }
        if (tokens.get(0).getCode().equals(TokenCode.BREAKTK)) {
            return new Stmt(new StmtBreakParser(tokens, symbolTable).parseStmtBreak());
        }
        if (tokens.get(0).getCode().equals(TokenCode.CONTINUETK)) {
            return new Stmt(new StmtContinueParser(tokens, symbolTable).parseStmtContinue());
        }
        if (tokens.get(0).getCode().equals(TokenCode.RETURNTK)) {
            return new Stmt(new StmtReturnParser(tokens, symbolTable).parseStmtReturn());
        }
        if (tokens.get(0).getCode().equals(TokenCode.PRINTFTK)) {
            return new Stmt(new StmtPrintfParser(tokens, symbolTable).parseStmtPrintf());
        }
        if (tokens.get(0).getCode().equals(TokenCode.LBRACE)) {
            this.symbolTable = new SymbolTable(this.symbolTable);
            this.symbolTable.setField(this.symbolTable.getField() + 1);
            return new Stmt(new StmtBlock(new BlockParser(tokens, symbolTable).parseBlock()));
        }
        if (tokens.get(0).getCode().equals(TokenCode.LPARENT) || tokens.get(0).getCode().equals(TokenCode.INTCON) ||
                tokens.get(0).getCode().equals(TokenCode.PLUS) || tokens.get(0).getCode().equals(TokenCode. MINU)) {
            return new Stmt(new StmtExpParser(tokens, symbolTable).parseStmtExp());
        }
        int i = 0;
        while (!tokens.get(i).getCode().equals(TokenCode.SEMICN)) {
            if (tokens.get(i).getCode().equals(TokenCode.ASSIGN)) {
                if (tokens.get(i + 1).getCode().equals(TokenCode.GETINTTK)) {
                    return new Stmt(new StmtLValAssignGetIntParser(tokens, symbolTable).parseStmtLValAssignGetInt());
                }
                if (tokens.get(i + 1).getCode().equals(TokenCode.GETCHARTK)) {
                    return new Stmt(new StmtLValAssignGetCharParser(tokens, symbolTable).parseStmtLValAssignGetChar());
                }
                return new Stmt(new StmtLValAssignExpParser(tokens, symbolTable).parseStmtLValAssignExp());
            }
            i++;
        }
        return new Stmt(new StmtExpParser(tokens, symbolTable).parseStmtExp());
    }
}
