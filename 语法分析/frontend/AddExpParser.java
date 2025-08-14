package frontend;

import java.util.ArrayList;

public class AddExpParser {
    private ArrayList<Token> tokens;

    public AddExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public AddExp parseAddExp() {

        MulExp mulExp = new MulExpParser(tokens).parseMulExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<MulExp> mulExps = new ArrayList<>();

        while (tokens.get(0).getCode().equals(Code.PLUS) || tokens.get(0).getCode().equals(Code.MINU)) {

            ops.add(tokens.remove(0));

            mulExps.add(new MulExpParser(tokens).parseMulExp());
        }
        return new AddExp(mulExp, ops, mulExps);
    }
}
