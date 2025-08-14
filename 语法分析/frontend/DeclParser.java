package frontend;

import java.util.ArrayList;

public class DeclParser {
    private ArrayList<Token> tokens;

    public DeclParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Decl parseDecl() {
        if (tokens.get(0).getCode().equals(Code.CONSTTK)) {
            return new Decl(new ConstDeclParser(tokens).parseConstDecl());
        }
        return new Decl(new VarDeclParser(tokens).parseVarDecl());
    }
}
