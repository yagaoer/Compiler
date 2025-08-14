package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor;

import frontend.symbol.SymbolTable;
import frontend.lexer.Token;
import frontend.symbol.exp.expFactor.unaryExp.UnaryExp;
import frontend.symbol.exp.expFactor.unaryExp.UnaryExpParser;

import java.util.ArrayList;

public class UnaryExpOpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public UnaryExpOpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public UnaryExpOp parseUnaryExpOp() {

        UnaryOp unaryOp = new UnaryOpParser(tokens).parseUnaryOp();

        UnaryExp unaryExp = new UnaryExpParser(tokens, symbolTable).parseUnaryExp();

        return new UnaryExpOp(unaryOp, unaryExp);
    }
}
