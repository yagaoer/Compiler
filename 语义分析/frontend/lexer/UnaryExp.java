package frontend.lexer;

import frontend.SymbolCode;

public class UnaryExp {
    private final String name = "<UnaryExp>";
    private UnaryExpFactor unaryExpFactor;

    public UnaryExp(UnaryExpFactor unaryExpFactor) {
        this.unaryExpFactor = unaryExpFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unaryExpFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return this.unaryExpFactor.getSymbolCode();
    }
}
