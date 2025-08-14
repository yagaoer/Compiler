package frontend;

import java.util.ArrayList;

public class LOrExpParser {
    private ArrayList<Token> tokens;

    public LOrExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public LOrExp parseLOrExp() {

        LAndExp lAndExp = new LAndExpParser(tokens).parseLAndExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<LAndExp> lAndExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(Code.OR)) {

            ops.add(tokens.remove(0));

            lAndExps.add(new LAndExpParser(tokens).parseLAndExp());
        }

        return new LOrExp(lAndExp, ops, lAndExps);
    }
}
