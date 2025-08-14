package frontend.symbol.stmt.stmtFactor.stmtContinue;

import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StmtContinueParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtContinueParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtContinue parseStmtContinue() {

        Token continueTk = tokens.remove(0);
        mError(continueTk);

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        return new StmtContinue(continueTk, semicn);
    }

    private void mError(Token continueTk) {
        if (!symbolTable.isInCycle()) {
            new Token("m", continueTk.getLine());
        }
    }
}
