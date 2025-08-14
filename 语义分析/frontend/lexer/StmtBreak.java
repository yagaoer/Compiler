package frontend.lexer;

public class StmtBreak implements StmtFactor {
    private Token breakTk; // 'break'
    private Token semicn; // ';'

    public StmtBreak(Token breakTk, Token semicn) {
        this.breakTk = breakTk;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.breakTk.toString()).append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {
    }
}
