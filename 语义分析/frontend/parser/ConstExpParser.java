package frontend.parser;

import frontend.SymbolTable;
import frontend.lexer.AddExp;
import frontend.lexer.ConstExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class ConstExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public ConstExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;

    }

    public ConstExp parseConstExp() {
        AddExp addExp = new AddExpParser(tokens, symbolTable).parseAddExp();
        return new ConstExp(addExp);
    }
}
