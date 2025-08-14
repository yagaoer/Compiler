package frontend;

import java.util.ArrayList;

public class FuncDefParser {
    private ArrayList<Token> tokens;

    public FuncDefParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public FuncDef parseFuncDef() {

        FuncType funcType = new FuncTypeParser(tokens).parseFuncType();

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lParent = tokens.remove(0);


        FuncFParams funcFParams = null;
        if (!tokens.get(0).getCode().equals(Code.RPARENT)) {
            funcFParams = new FuncFParamsParser(tokens).parseFuncFParams();
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        Block block = new BlockParser(tokens).parseBlock();

        return new FuncDef(funcType, ident, lParent, funcFParams, rParent, block);
    }
}
