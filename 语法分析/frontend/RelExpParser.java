package frontend;

import java.util.ArrayList;

public class RelExpParser {
    private ArrayList<Token> tokens;

    public RelExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public RelExp parseRelExp() {

        AddExp addExp = new AddExpParser(tokens).parseAddExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<AddExp> addExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(Code.LSS) || tokens.get(0).getCode().equals(Code.GRE) ||
                tokens.get(0).getCode().equals(Code.LEQ) || tokens.get(0).getCode().equals(Code.GEQ)) {

            ops.add(tokens.remove(0));

            addExps.add(new AddExpParser(tokens).parseAddExp());
        }

        return new RelExp(addExp, ops, addExps);
    }
}
