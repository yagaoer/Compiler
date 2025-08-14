package frontend;

public class StmtExp implements StmtFactor {
    /*
    [Exp] ';'
     */
    private Exp exp;
    private Token semicn; // ';'

    public StmtExp(Exp exp, Token semicn) {
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.exp != null) {
            sb.append(this.exp.toString());
        }
        sb.append(this.semicn.toString());
        return sb.toString();
    }
}
