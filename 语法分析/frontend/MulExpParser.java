package frontend;

import java.util.ArrayList;

public class MulExpParser {
    private ArrayList<Token> tokens;

    public MulExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public MulExp parseMulExp() {

        UnaryExp unaryExp = new UnaryExpParser(tokens).parseUnaryExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<UnaryExp> unaryExps = new ArrayList<>();

        while (tokens.get(0).getCode().equals(Code.MULT) ||
                tokens.get(0).getCode().equals(Code.DIV) ||
                tokens.get(0).getCode().equals(Code.MOD)) {

            ops.add(tokens.remove(0));

            unaryExps.add(new UnaryExpParser(tokens).parseUnaryExp());
        }

        return new MulExp(unaryExp, ops, unaryExps);
    }
}
