package frontend.lexer;

public class Stmt implements BlockItemFactor {
    private final String name = "<Stmt>";
    private StmtFactor stmtFactor;

    public Stmt(StmtFactor stmtFactor) {
        this.stmtFactor = stmtFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.stmtFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public void fError() {
        this.stmtFactor.fError();
    }

    @Override
    public void gError() {

    }

    public StmtFactor getStmtFactor() {
        return stmtFactor;
    }
}
