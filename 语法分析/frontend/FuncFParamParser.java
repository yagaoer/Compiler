package frontend;

import java.util.ArrayList;

public class FuncFParamParser {
    private ArrayList<Token> tokens;

    public FuncFParamParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public FuncFParam parseFuncFParam() {

        BType bType = new BTypeParser(tokens).parseBtype();

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        Token rBrack = null;
        if (tokens.get(0).getCode().equals(Code.LBRACK)) {

            lBrack = tokens.remove(0);


            if (tokens.get(0).getCode().equals(Code.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RBRACK);
            }
        }
        return new FuncFParam(bType, ident, lBrack, rBrack);
    }
}
