package frontend;

import java.util.ArrayList;

public class ConstDeclParser {
    private ArrayList<Token> tokens;

    public ConstDeclParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ConstDecl parseConstDecl() {
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<ConstDef> constDefs = new ArrayList<>();

        Token constTk = tokens.remove(0);

        BType btype = new BTypeParser(tokens).parseBtype();

        ConstDef constDef = new ConstDefParser(tokens).parseConstDef();

        while (tokens.get(0).getCode().equals(Code.COMMA)) {

            commas.add(tokens.remove(0));

            constDefs.add(new ConstDefParser(tokens).parseConstDef());
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new ConstDecl(constTk, btype, constDef, commas, constDefs, semicn);
    }

}
