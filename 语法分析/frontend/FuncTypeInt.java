package frontend;

public class FuncTypeInt implements FuncTypeFactor {
    private Token token; // 'int'

    public FuncTypeInt(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.toString();
    }
}
