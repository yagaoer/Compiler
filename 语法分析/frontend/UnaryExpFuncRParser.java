package frontend;

import java.util.ArrayList;

public class UnaryExpFuncRParser {
    private ArrayList<Token> tokens;

    public UnaryExpFuncRParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }


    public UnaryExpFuncR parseUnaryFuncRExp() {

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lParent = tokens.remove(0);


        FuncRParams funcRParams = null;
        if (!tokens.get(0).getCode().equals(Code.RPARENT)) {
            funcRParams = new FuncRParamsParser(tokens).parseFuncRParams();
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        return new UnaryExpFuncR(ident, lParent, funcRParams, rParent);
    }
}
