package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.EqExp;
import frontend.lexer.LAndExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class LAndExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public LAndExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public LAndExp parseLAndExp() {

        EqExp eqExp = new EqExpParser(tokens, symbolTable).parseEqExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<EqExp> eqExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(TokenCode.AND)) { // '&&'

            ops.add(tokens.remove(0));

            eqExps.add(new EqExpParser(tokens, symbolTable).parseEqExp());
        }

        return new LAndExp(eqExp, ops, eqExps);
    }
}
