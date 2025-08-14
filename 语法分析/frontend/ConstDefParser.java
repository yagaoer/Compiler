package frontend;

import java.util.ArrayList;

public class ConstDefParser {
    private ArrayList<Token> tokens;

    public ConstDefParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ConstDef parseConstDef() {

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        ConstExp constExp = null;
        Token rBrack = null;
        if (tokens.get(0).getCode().equals(Code.LBRACK)) {

            lBrack = tokens.remove(0);

            constExp = new ConstExpParser(tokens).parseConstExp();

            if (tokens.get(0).getCode().equals(Code.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RBRACK);
            }
        }

        Token assign = tokens.remove(0);

        ConstInitVal constInitval = new ConstInitValParser(tokens).parseConstInitVal();

        return new ConstDef(ident, lBrack, constExp, rBrack, assign, constInitval);
    }
}
