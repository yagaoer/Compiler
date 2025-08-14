package frontend;

import java.util.ArrayList;

public class VarDeclParser {
    private ArrayList<Token> tokens;

    public VarDeclParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public VarDecl parseVarDecl() {

        BType btype = new BTypeParser(tokens).parseBtype();

        VarDef varDef = new VarDefParser(tokens).parseVarDef();

        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<VarDef> varDefs = new ArrayList<>();
        while (tokens.get(0).getCode().equals(Code.COMMA)) {

            commas.add(tokens.remove(0));

            varDefs.add(new VarDefParser(tokens).parseVarDef());
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new VarDecl(btype, varDef, commas, varDefs, semicn);
    }
}
