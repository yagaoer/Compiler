package frontend.symbol.stmt.stmtFactor.stmtFor;

import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.exp.Exp;
import frontend.symbol.exp.ExpParser;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LValParser;
import frontend.symbol.stmt.stmtFactor.stmtFor.ForStmt;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LVal;
import frontend.lexer.Token;

import java.util.ArrayList;

public class ForStmtParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public ForStmtParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public ForStmt parseForStmt() {

        LVal lVal = new LValParser(tokens, symbolTable).parseLVal();
        hError(lVal);

        Token assign = tokens.remove(0);

        Exp exp = new ExpParser(tokens, symbolTable).parseExp();

        return new ForStmt(lVal, assign, exp, symbolTable);
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
