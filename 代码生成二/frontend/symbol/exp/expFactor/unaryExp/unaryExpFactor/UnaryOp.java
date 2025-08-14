package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor;

import frontend.lexer.Token;

public class UnaryOp {
    private final String name = "<UnaryOp>";
    private Token token;

    public UnaryOp(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(token.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
