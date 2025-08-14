package frontend.lexer;

public class FuncFParam {
    private final String name = "<FuncFParam>";
    private BType btype; // BType
    private Ident ident; // Ident
    private Token lBrack;
    private Token rBrack; // ['[' ']']

    public FuncFParam(BType btype, Ident ident, Token lBrack, Token rBrack) {
        this.btype = btype;
        this.ident = ident;
        this.lBrack = lBrack;
        this.rBrack = rBrack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.btype.toString()).append(this.ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.rBrack.toString());
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
