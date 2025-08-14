package frontend.lexer;

public class VarDefWithNull implements VarDefFactor {
    private Ident ident; //  Ident
    private Token lBrack;
    private ConstExp constExp;
    private Token rBrack; //  [ '[' ConstExp ']' ]

    public VarDefWithNull(Ident ident, Token lBrack, ConstExp constExp, Token rBrack) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.constExp = constExp;
        this.rBrack = rBrack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.constExp.toString()).append(this.rBrack.toString());
        }
        return sb.toString();
    }
}
