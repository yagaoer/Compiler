package frontend.lexer;

import frontend.SymbolCode;

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
}
