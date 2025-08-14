package frontend.symbol.stmt.stmtFactor.stmtBreak;

import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StmtBreakParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtBreakParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtBreak parseStmtBreak() {

        Token breakTk = tokens.remove(0);
        mError(breakTk);

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        return new StmtBreak(breakTk, semicn);
    }

    private void mError(Token breakTk) {
        if (!symbolTable.isInCycle()) {
            new Token("m", breakTk.getLine());
        }
    }
}
