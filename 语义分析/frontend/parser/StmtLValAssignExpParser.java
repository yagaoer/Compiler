package frontend.parser;

import frontend.*;
import frontend.lexer.Exp;
import frontend.lexer.LVal;
import frontend.lexer.StmtLValAssignExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class StmtLValAssignExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtLValAssignExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtLValAssignExp parseStmtLValAssignExp() {

        LVal lval = new LValParser(tokens, symbolTable).parseLVal();
        hError(lval);

        Token assign = tokens.remove(0);

        Exp exp = new ExpParser(tokens, symbolTable).parseExp();

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        return new StmtLValAssignExp(lval, assign, exp, semicn);
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
