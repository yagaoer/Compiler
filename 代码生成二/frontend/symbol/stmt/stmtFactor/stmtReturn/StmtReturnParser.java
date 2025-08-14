package frontend.symbol.stmt.stmtFactor.stmtReturn;

import frontend.symbol.exp.ExpParser;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.symbol.exp.Exp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StmtReturnParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtReturnParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtReturn parseStmtReturn() {

        Token returnTk = tokens.remove(0);

        Exp exp = null;
        if (tokens.get(0).getCode().equals(TokenCode.IDENFR) ||
                tokens.get(0).getCode().equals(TokenCode.INTCON) ||
                tokens.get(0).getCode().equals(TokenCode.CHRCON) ||
                tokens.get(0).getCode().equals(TokenCode.LPARENT) ||
                tokens.get(0).getCode().equals(TokenCode.PLUS) ||
                tokens.get(0).getCode().equals(TokenCode.MINU) ||
                tokens.get(0).getCode().equals(TokenCode.NOT) ) {
            exp = new ExpParser(tokens, symbolTable).parseExp();
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        return new StmtReturn(returnTk, exp, semicn);
    }


}
