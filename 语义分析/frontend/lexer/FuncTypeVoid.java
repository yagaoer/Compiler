package frontend.lexer;

public class FuncTypeVoid implements FuncTypeFactor {
    private Token token; // 'void'

    public FuncTypeVoid(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.toString();
    }
}
