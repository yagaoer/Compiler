package frontend;

import frontend.UnaryExpFactor;

public class UnaryExpFuncR implements UnaryExpFactor {
    /*
     Ident '(' [FuncRParams] ')'
     */
    private Ident ident; //  Ident
    private Token lParent; // '('
    private FuncRParams funcRParams; // [FuncRParams]
    private Token rParent; // ')'

    public UnaryExpFuncR(Ident ident, Token lParent, FuncRParams funcRParams, Token rParent) {
        this.ident = ident;
        this.lParent = lParent;
        this.funcRParams = funcRParams;
        this.rParent = rParent;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString()).append(lParent.toString());
        if (funcRParams != null) {
            sb.append(this.funcRParams.toString());
        }
        sb.append(rParent.toString());
        return sb.toString();
    }
}
