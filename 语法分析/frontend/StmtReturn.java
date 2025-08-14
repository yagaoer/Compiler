package frontend;

public class StmtReturn implements StmtFactor {
    /*
    'return' [Exp] ';'
     */
    private Token returnTk; // 'return'
    private Exp exp; // [Exp]
    private Token semicn; // ';'

    public StmtReturn(Token returnTk, Exp exp, Token semicn) {
        this.returnTk = returnTk;
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.returnTk.toString());
        if (this.exp != null) {
            sb.append(this.exp.toString());
        }
        sb.append(this.semicn.toString());
        return sb.toString();
    }
}
