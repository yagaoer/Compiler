package frontend.symbol.func.funcType;

import frontend.lexer.Token;

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
