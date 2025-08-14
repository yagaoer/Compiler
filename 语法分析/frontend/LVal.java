package frontend;

public class LVal implements PrimaryExpFactor {
    /*
     Ident ['[' Exp ']']
     */
    private final String name = "<LVal>";
    private Ident ident; // Ident
    private Token lBrack; // '['
    private Exp exp; // Exp
    private Token rBrack; // ']'

    public LVal(Ident ident, Token lBrack, Exp exp, Token rBrack) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.exp = exp;
        this.rBrack = rBrack;
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
}
