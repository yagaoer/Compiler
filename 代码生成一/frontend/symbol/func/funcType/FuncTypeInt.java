package frontend.symbol.func.funcType;

import frontend.lexer.Token;

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
