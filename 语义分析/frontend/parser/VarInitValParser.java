package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.Token;
import frontend.lexer.VarInitVal;

import java.util.ArrayList;

public class VarInitValParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public VarInitValParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public VarInitVal parseInitVal() {

        if (tokens.get(0).getCode().equals(TokenCode.LBRACE)) {
            return new VarInitVal(new VarInitValArrayParser(tokens, symbolTable).parseVarInitValArray());
        }

        if (tokens.get(0).getCode().equals(TokenCode.STRCON)) {
            return new VarInitVal(new StringConstParser(tokens).parseStringConst());
        }

        return new VarInitVal(new ExpParser(tokens, symbolTable).parseExp());
    }
}
