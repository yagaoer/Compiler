package frontend;

import java.util.ArrayList;

public class LAndExpParser {
    private ArrayList<Token> tokens;

    public LAndExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public LAndExp parseLAndExp() {

        EqExp eqExp = new EqExpParser(tokens).parseEqExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<EqExp> eqExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(Code.AND)) { // '&&'

            ops.add(tokens.remove(0));

            eqExps.add(new EqExpParser(tokens).parseEqExp());
        }

        return new LAndExp(eqExp, ops, eqExps);
    }
}
