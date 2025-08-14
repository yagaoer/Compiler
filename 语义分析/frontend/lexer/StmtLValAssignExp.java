package frontend.lexer;

public class StmtLValAssignExp implements StmtFactor {
    private LVal lval; //  LVal
    private Token assign; // '='
    private Exp exp; // Exp
    private Token semicn; // ';'

    public StmtLValAssignExp(LVal lval, Token assign, Exp exp, Token semicn) {
        this.lval = lval;
        this.assign = assign;
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lval.toString()).append(this.assign.toString())
                .append(this.exp.toString()).append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {

    }
}
