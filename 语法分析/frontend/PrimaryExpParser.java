package frontend;

import java.util.ArrayList;

public class PrimaryExpParser {
    private ArrayList<Token> tokens;

    public PrimaryExpParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public PrimaryExp parsePrimaryExp() {

        if (tokens.get(0).getCode().equals(Code.LPARENT)) {
           return new PrimaryExp(new PrimaryExpExpParser(tokens).parsePrimaryExpExp());
        }
        if (tokens.get(0).getCode().equals(Code.IDENFR)) {
            return new PrimaryExp(new LValParser(tokens).parseLVal());
        }
        if (tokens.get(0).getCode().equals(Code.INTCON)) {
            return new PrimaryExp(new NumberParser(tokens).parseNumber());
        }
        return new PrimaryExp(new CharacterParser(tokens).parseCharacter());
    }
}
