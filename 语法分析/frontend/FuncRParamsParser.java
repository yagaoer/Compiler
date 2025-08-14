package frontend;

import java.util.ArrayList;

public class FuncRParamsParser {
    private ArrayList<Token> tokens;

    public FuncRParamsParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public FuncRParams parseFuncRParams() {

        Exp exp = new ExpParser(tokens).parseExp();

        ArrayList<Token> commas = new ArrayList<>();

        ArrayList<Exp> exps = new ArrayList<>();

        while (tokens.get(0).getCode().equals(Code.COMMA)) {

            commas.add(tokens.remove(0));

            exps.add(new ExpParser(tokens).parseExp());
        }

        return new FuncRParams(exp, commas, exps);
    }
}
