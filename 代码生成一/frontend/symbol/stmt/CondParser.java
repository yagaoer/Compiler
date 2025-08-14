package frontend.symbol.stmt;

import frontend.symbol.exp.expFactor.LOrExpParser;
import frontend.symbol.SymbolTable;
import frontend.symbol.exp.expFactor.LOrExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class CondParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public CondParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public Cond parseCond() {
        LOrExp lorExp = new LOrExpParser(tokens, symbolTable).parseLOrExp();
        return new Cond(lorExp, symbolTable);
    }
}
