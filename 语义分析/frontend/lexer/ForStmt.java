package frontend.lexer;

public class ForStmt {
    /*
     LVal '=' Exp
     */
    private final String name = "<ForStmt>";
    private LVal lval; //  LVal
    private Token assign; // '='
    private Exp exp; // Exp

    public ForStmt(LVal lval, Token assign, Exp exp) {
        this.lval = lval;
        this.assign = assign;
        this.exp = exp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lval.toString()).append(this.assign.toString()).append(this.exp.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }
}
