package frontend.symbol.stmt.stmtFactor.stmtLVal;

import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LValParser;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LVal;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StmtLValAssignGetIntParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtLValAssignGetIntParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtLValAssignGetInt parseStmtLValAssignGetInt() {

        LVal lval = new LValParser(tokens, symbolTable).parseLVal();
        hError(lval);

        Token assign = tokens.remove(0);

        Token getInt = tokens.remove(0);

        Token lParent = tokens.remove(0);

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        return new StmtLValAssignGetInt(lval, assign, getInt, lParent, rParent, semicn);
    }

    private void hError(LVal lval) {
        if (lval.getSymbolCode().equals(SymbolCode.ConstChar) ||
                lval.getSymbolCode().equals(SymbolCode.ConstInt) ||
                lval.getSymbolCode().equals(SymbolCode.ConstCharArray) ||
                lval.getSymbolCode().equals(SymbolCode.ConstIntArray)) {
            new Token("h", lval.getIdent().getToken().getLine());
        }
    }
}
