package frontend;

public class StringConst implements VarInitValFactor, ConstInitValFactor {
    private Token token; // 标识符单词

    public StringConst(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.toString();
    }
}
