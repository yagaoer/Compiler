package frontend.lexer;

import frontend.SymbolCode;

public class LVal implements PrimaryExpFactor {
    /*
     Ident ['[' Exp ']']
     */
    private final String name = "<LVal>";
    private Ident ident; // Ident
    private Token lBrack; // '['
    private Exp exp; // Exp
    private Token rBrack; // ']'
    private SymbolCode symbolCode;

    public LVal(Ident ident, Token lBrack, Exp exp, Token rBrack, SymbolCode symbolCode) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.exp = exp;
        this.rBrack = rBrack;
        this.symbolCode = symbolCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (this.lBrack != null) {
            sb.append(lBrack.toString()).append(exp.toString()).append(rBrack.toString());
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return symbolCode;
    }

    public Ident getIdent() {
        return ident;
    }
}
