package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.MulExp;
import frontend.lexer.Token;
import frontend.lexer.UnaryExp;

import java.util.ArrayList;

public class MulExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public MulExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public MulExp parseMulExp() {

        UnaryExp unaryExp = new UnaryExpParser(tokens, symbolTable).parseUnaryExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<UnaryExp> unaryExps = new ArrayList<>();

        while (tokens.get(0).getCode().equals(TokenCode.MULT) ||
                tokens.get(0).getCode().equals(TokenCode.DIV) ||
                tokens.get(0).getCode().equals(TokenCode.MOD)) {

            ops.add(tokens.remove(0));

            unaryExps.add(new UnaryExpParser(tokens, symbolTable).parseUnaryExp());
        }

        return new MulExp(unaryExp, ops, unaryExps);
    }
}
