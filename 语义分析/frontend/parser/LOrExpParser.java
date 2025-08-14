package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.LAndExp;
import frontend.lexer.LOrExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class LOrExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public LOrExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public LOrExp parseLOrExp() {

        LAndExp lAndExp = new LAndExpParser(tokens, symbolTable).parseLAndExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<LAndExp> lAndExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(TokenCode.OR)) {

            ops.add(tokens.remove(0));

            lAndExps.add(new LAndExpParser(tokens, symbolTable).parseLAndExp());
        }

        return new LOrExp(lAndExp, ops, lAndExps);
    }
}
