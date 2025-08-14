package frontend.lexer;

public class VarDefWithAssign implements VarDefFactor {
    private Ident ident; //  Ident
    private Token lBrack;
    private ConstExp constExp;
    private Token rBrack; // [ '[' ConstExp ']' ]
    private Token assign; // '='
    private VarInitVal varInitVal; // InitVal

    public VarDefWithAssign(Ident ident, Token lBrack, ConstExp constExp, Token rBrack,
                            Token assign, VarInitVal varInitVal) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.constExp = constExp;
        this.rBrack = rBrack;
        this.assign = assign;
        this.varInitVal = varInitVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.constExp.toString()).append(this.rBrack.toString());
        }
        sb.append(this.assign.toString()).append(this.varInitVal.toString());
        return sb.toString();
    }
}
