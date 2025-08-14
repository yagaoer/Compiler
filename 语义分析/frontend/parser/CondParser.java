package frontend.parser;

import frontend.SymbolTable;
import frontend.lexer.Cond;
import frontend.lexer.LOrExp;
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
        return new Cond(lorExp);
    }
}
