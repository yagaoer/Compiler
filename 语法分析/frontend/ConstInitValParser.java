package frontend;

import frontend.Token;

import java.util.ArrayList;

public class ConstInitValParser {

    private ArrayList<Token> tokens;

    public ConstInitValParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }
    public ConstInitVal parseConstInitVal() {
        if (tokens.get(0).getCode().equals(Code.LBRACE)) {
            return new ConstInitVal(new ConstInitValArrayParser(tokens).parseConstInitValArray());
        }

        if (tokens.get(0).getCode().equals(Code.STRCON)) {
            return new ConstInitVal(new StringConstParser(tokens).parseStringConst());
        }

        return new ConstInitVal(new ConstExpParser(tokens).parseConstExp());
    }
}
