package frontend.lexer;

import frontend.SymbolCode;

public class UnaryExpFuncR implements UnaryExpFactor {
    /*
     Ident '(' [FuncRParams] ')'
     */
    private Ident ident; //  Ident
    private Token lParent; // '('
    private FuncRParams funcRParams; // [FuncRParams]
    private Token rParent; // ')'
    private SymbolCode symbolCode;

    public UnaryExpFuncR(Ident ident, Token lParent, FuncRParams funcRParams, Token rParent, SymbolCode symbolCode) {
        this.ident = ident;
        this.lParent = lParent;
        this.funcRParams = funcRParams;
        this.rParent = rParent;
        this.symbolCode = symbolCode;
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

    @Override
    public SymbolCode getSymbolCode() {
        return symbolCode;
    }
}
