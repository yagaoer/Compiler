package frontend;

public class StmtLValAssignGetInt implements StmtFactor {
    /*
     LVal '=' 'getint''('')'';'
     */
    private LVal lval; //  LVal
    private Token assign; // '='
    private Token getInt; // 'getint'
    private Token lParent; // '('
    private Token rParent; // ')'
    private Token semicn; // ';'

    public StmtLValAssignGetInt(LVal lval, Token assign, Token getInt, Token lParent, Token rParent, Token semicn) {
        this.lval = lval;
        this.assign = assign;
        this.getInt = getInt;
        this.lParent = lParent;
        this.rParent = rParent;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lval.toString()).append(this.assign.toString()).append(this.getInt.toString())
                .append(this.lParent.toString()).append(this.rParent.toString())
                .append(this.semicn.toString());
        return sb.toString();
    }
}
