package frontend;

import java.util.ArrayList;

public class LValParser {
    private ArrayList<Token> tokens;

    public LValParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public LVal parseLVal() {

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        Exp exp = null;
        Token rBrack = null;
        if (tokens.get(0).getCode().equals(Code.LBRACK)) {

            lBrack = tokens.remove(0);

            exp = new ExpParser(tokens).parseExp();

            if (tokens.get(0).getCode().equals(Code.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RBRACK);
            }
        }

        return new LVal(ident, lBrack, exp, rBrack);
    }
}
