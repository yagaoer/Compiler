package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor;

import frontend.lexer.Token;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.UnaryOp;

import java.util.ArrayList;

public class UnaryOpParser {
    private ArrayList<Token> tokens;

    public UnaryOpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public UnaryOp parseUnaryOp() {

        Token token = tokens.remove(0);

        return new UnaryOp(token);
    }
}
