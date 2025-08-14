package frontend.symbol.exp.expFactor;

import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;

import java.util.ArrayList;

public class AddExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public AddExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public AddExp parseAddExp() {

        MulExp mulExp = new MulExpParser(tokens, symbolTable).parseMulExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<MulExp> mulExps = new ArrayList<>();

        while (tokens.get(0).getCode().equals(TokenCode.PLUS) || tokens.get(0).getCode().equals(TokenCode.MINU)) {

            ops.add(tokens.remove(0));

            mulExps.add(new MulExpParser(tokens, symbolTable).parseMulExp());
        }
        return new AddExp(mulExp, ops, mulExps, symbolTable);
    }
}
