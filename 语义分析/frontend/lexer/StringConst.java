package frontend.lexer;

public class StringConst implements VarInitValFactor, ConstInitValFactor {
    private Token token; // 标识符单词

    public StringConst(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.toString();
    }

    public Token getToken() {
        return token;
    }
}
