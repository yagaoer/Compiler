package frontend;

public class StmtContinue implements StmtFactor {
    private Token continueTk; // 'continue'
    private Token semicn; // ';'

    public StmtContinue(Token continueTk, Token semicn) {
        this.continueTk = continueTk;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.continueTk.toString()).append(this.semicn.toString());
        return sb.toString();
    }
}
