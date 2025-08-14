package frontend;

import java.util.ArrayList;

public class VarInitValParser {
    private ArrayList<Token> tokens;

    public VarInitValParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public VarInitVal parseInitVal() {

        if (tokens.get(0).getCode().equals(Code.LBRACE)) {
            return new VarInitVal(new VarInitValArrayParser(tokens).parseVarInitValArray());
        }

        if (tokens.get(0).getCode().equals(Code.STRCON)) {
            return new VarInitVal(new StringConstParser(tokens).parseStringConst());
        }

        return new VarInitVal(new ExpParser(tokens).parseExp());
    }
}
