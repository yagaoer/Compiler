package frontend;

public class Ident {
    private Token token; // ident

    public Ident(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.toString();
    }
}
