package frontend.lexer;

public class UnaryOp {
    private final String name = "<UnaryOp>";
    private Token token;

    public UnaryOp(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(token.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
