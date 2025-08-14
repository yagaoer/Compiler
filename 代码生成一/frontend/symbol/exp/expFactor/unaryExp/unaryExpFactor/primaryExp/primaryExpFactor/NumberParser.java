package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor;

import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.Number;
import frontend.lexer.Token;

import java.util.ArrayList;

public class NumberParser {
    private ArrayList<Token> tokens;
    public NumberParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Number parseNumber() {

        Token intConst = tokens.remove(0);

        return new Number(intConst);
    }
}
