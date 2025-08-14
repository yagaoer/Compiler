package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor;

import frontend.symbol.SymbolCode;
import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import llvm.Counter;

import java.util.ArrayList;

public class PrimaryExpExp implements PrimaryExpFactor {
    /*
    '(' Exp ')'
     */
    private Token lParent; // '('
    private Exp exp; // Exp
    private Token rParent; // ')'

    public PrimaryExpExp(Token lParent, Exp exp, Token rParent) {
        this.lParent = lParent;
        this.exp = exp;
        this.rParent = rParent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lParent.toString()).append(exp.toString()).append(rParent.toString());
        return sb.toString();
    }

    @Override
    public SymbolCode getSymbolCode() {
        return this.exp.getSymbolCode();
    }

    @Override
    public int toRes() {
        return this.exp.toRes();
    }

    @Override
    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        return this.exp.toInstructions(cnt, iType, strings);
    }
}
