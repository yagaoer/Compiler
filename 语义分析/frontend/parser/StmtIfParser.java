package frontend.parser;

import frontend.*;
import frontend.lexer.Cond;
import frontend.lexer.Stmt;
import frontend.lexer.StmtIf;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StmtIfParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtIfParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtIf parseStmtIf() {

        Token ifTk = tokens.remove(0);

        Token lParent = tokens.remove(0);

        Cond cond = new CondParser(tokens, symbolTable).parseCond();

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        Stmt stmt1 = new StmtParser(tokens, symbolTable).parseStmt();

        Token elseTk = null;
        Stmt stmt2 = null;
        if (tokens.get(0).getCode().equals(TokenCode.ELSETK)) {

            elseTk = tokens.remove(0);

            stmt2 = new StmtParser(tokens, symbolTable).parseStmt();
        }

        return new StmtIf(ifTk, lParent, cond, rParent, stmt1, elseTk, stmt2);
    }
}
