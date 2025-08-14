package frontend;

import java.util.ArrayList;

public class StmtPrintfParser {
    private ArrayList<Token> tokens;

    public StmtPrintfParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public StmtPrintf parseStmtPrintf() {

        Token printf = tokens.remove(0);

        Token lParent = tokens.remove(0);

        StringConst stringConst = new StringConstParser(tokens).parseStringConst();

        ArrayList<Token> commmas = new ArrayList<>();
        ArrayList<Exp> exps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(Code.COMMA)) {

            commmas.add(tokens.remove(0));

            exps.add(new ExpParser(tokens).parseExp());
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(Code.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.RPARENT);
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(Code.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), Code.SEMICN);
        }

        return new StmtPrintf(printf, lParent, stringConst, commmas, exps, rParent, semicn);
    }
}
