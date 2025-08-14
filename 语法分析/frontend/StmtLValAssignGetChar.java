package frontend;

public class StmtLValAssignGetChar implements StmtFactor {
    /*
    LVal '=' 'getchar''('')'';'
     */
    /*
     LVal '=' 'getint''('')'';'
     */
    private LVal lval; //  LVal
    private Token assign; // '='
    private Token getChar; // 'getchar'
    private Token lParent; // '('
    private Token rParent; // ')'
    private Token semicn; // ';'

    public StmtLValAssignGetChar(LVal lval, Token assign, Token getChar, Token lParent, Token rParent, Token semicn) {
        this.lval = lval;
        this.assign = assign;
        this.getChar = getChar;
        this.lParent = lParent;
        this.rParent = rParent;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lval.toString()).append(this.assign.toString()).append(this.getChar.toString())
                .append(this.lParent.toString()).append(this.rParent.toString())
                .append(this.semicn.toString());
        return sb.toString();
    }
}
