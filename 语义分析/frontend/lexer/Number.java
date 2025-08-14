package frontend.lexer;

import frontend.SymbolCode;

public class Number implements PrimaryExpFactor {
    private final String name = "<Number>";
    private Token intConst;

    public Number(Token intConst) {
        this.intConst = intConst;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append(this.intConst.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public SymbolCode getSymbolCode() {
        return SymbolCode.ConstInt;
    }
}
