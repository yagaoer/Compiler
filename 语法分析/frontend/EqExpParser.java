package frontend;

import java.util.ArrayList;

public class EqExpParser {
    private ArrayList<Token> tokens;

    public EqExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public EqExp parseEqExp() {

        RelExp relExp = new RelExpParser(tokens).parseRelExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<RelExp> relExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(Code.EQL) ||
                tokens.get(0).getCode().equals(Code.NEQ)) {

            ops.add(tokens.remove(0));

            relExps.add(new RelExpParser(tokens).parseRelExp());
        }

        return new EqExp(relExp, ops, relExps);
    }
}
