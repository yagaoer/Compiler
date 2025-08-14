package frontend;

public class UnaryExpOp implements UnaryExpFactor {
    /*
     UnaryOp UnaryExp
     */
    private UnaryOp unaryOp; // UnaryOp
    private UnaryExp unaryExp;// UnaryExp

    public UnaryExpOp(UnaryOp unaryOp, UnaryExp unaryExp) {
        this.unaryOp = unaryOp;
        this.unaryExp = unaryExp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unaryOp.toString()).append(this.unaryExp.toString());
        return sb.toString();
    }
}
