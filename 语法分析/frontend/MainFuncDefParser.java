package frontend;

import java.util.ArrayList;

public class MainFuncDefParser {
    private ArrayList<Token> tokens;

    public MainFuncDefParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public MainFuncDef parseMainFuncDef() {

        Token intTk = tokens.remove(0);

        Token mainTk = tokens.remove(0);

        Token lParent = tokens.remove(0);

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        Block block = new BlockParser(tokens).parseBlock();

        return new MainFuncDef(intTk, mainTk, lParent, rParent, block);
    }
}
