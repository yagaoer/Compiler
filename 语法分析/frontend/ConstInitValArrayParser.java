package frontend;

import java.util.ArrayList;

public class ConstInitValArrayParser {
    private ArrayList<Token> tokens;

    public ConstInitValArrayParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ConstInitValArray parseConstInitValArray() {

        Token lBrace = tokens.remove(0);


        ConstExp constExp = null;
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<ConstExp> constExps = new ArrayList<>();

        if (!tokens.get(0).getCode().equals(Code.RBRACE)) {

            constExp = new ConstExpParser(tokens).parseConstExp();
            while (tokens.get(0).getCode().equals(Code.COMMA)) {

                commas.add(tokens.remove(0));

                constExps.add(new ConstExpParser(tokens).parseConstExp());
            }
        }

        Token rBrace = tokens.remove(0);

        return new ConstInitValArray(lBrace, constExp, commas, constExps, rBrace);
    }
}
