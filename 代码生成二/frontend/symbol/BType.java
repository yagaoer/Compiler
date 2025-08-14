package frontend.symbol;

import frontend.lexer.Token;

public class BType {
    private Token token; // 'int' | 'char'

    public BType(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.token.toString());
        //sb.append(this.name).append("\n");
        return sb.toString();
    }

    public Token getToken() {
        return token;
    }
}
