package frontend;

import java.util.ArrayList;

public class VarInitValArrayParser {
    private ArrayList<Token> tokens;

    public VarInitValArrayParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public VarInitValArray parseVarInitValArray() {
        Token lBrace = tokens.remove(0);


        Exp exp = null;
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<Exp> exps = new ArrayList<>();

        if (!tokens.get(0).getCode().equals(Code.RBRACE)) {

            exp = new ExpParser(tokens).parseExp();

            while (tokens.get(0).getCode().equals(Code.COMMA)) {

                commas.add(tokens.remove(0));

                exps.add(new ExpParser(tokens).parseExp());
            }
        }

        Token rBrace = tokens.remove(0);

        return new VarInitValArray(lBrace, exp, commas, exps, rBrace);
    }
}
