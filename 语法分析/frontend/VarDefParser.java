package frontend;

import java.util.ArrayList;

public class VarDefParser {
    private ArrayList<Token> tokens;

    public VarDefParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public VarDef parseVarDef() {

        Ident ident = new IdentParser(tokens).parseIdent();

        Token lBrack = null;
        ConstExp constExp = null;
        Token rBrack = null;
        while (tokens.get(0).getCode().equals(Code.LBRACK)) {

            lBrack = tokens.remove(0);

            constExp = new ConstExpParser(tokens).parseConstExp();

            if (tokens.get(0).getCode().equals(Code.RBRACK)) {
                rBrack = tokens.remove(0);
            } else {
                rBrack = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RBRACK);
            }
        }


        if (tokens.get(0).getCode().equals(Code.ASSIGN)) {

            Token assign = tokens.remove(0);

            VarInitVal varInitVal = new VarInitValParser(tokens).parseInitVal();

           return new VarDef(new VarDefWithAssign(ident, lBrack, constExp, rBrack, assign, varInitVal));
        }

        return new VarDef(new VarDefWithNull(ident, lBrack, constExp, rBrack));
    }
}
