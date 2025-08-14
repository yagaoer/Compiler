package frontend;

public class FuncTypeChar implements FuncTypeFactor {
    private Token token; // 'char'

    public FuncTypeChar(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.toString();
    }
}
