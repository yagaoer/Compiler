package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.EqExp;
import frontend.lexer.RelExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class EqExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public EqExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public EqExp parseEqExp() {

        RelExp relExp = new RelExpParser(tokens, symbolTable).parseRelExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<RelExp> relExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(TokenCode.EQL) ||
                tokens.get(0).getCode().equals(TokenCode.NEQ)) {

            ops.add(tokens.remove(0));

            relExps.add(new RelExpParser(tokens, symbolTable).parseRelExp());
        }

        return new EqExp(relExp, ops, relExps);
    }
}
