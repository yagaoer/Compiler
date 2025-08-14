package frontend.symbol.decl.constDecl.constDef.constInitVal;

import frontend.symbol.exp.ConstExpParser;
import frontend.symbol.decl.StringConstParser;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;

import java.util.ArrayList;

public class ConstInitValParser {

    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public ConstInitValParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }
    public ConstInitVal parseConstInitVal() {
        if (tokens.get(0).getCode().equals(TokenCode.LBRACE)) {
            return new ConstInitVal(new ConstInitValArrayParser(tokens, symbolTable).parseConstInitValArray());
        }

        if (tokens.get(0).getCode().equals(TokenCode.STRCON)) {
            return new ConstInitVal(new StringConstParser(tokens).parseStringConst());
        }

        return new ConstInitVal(new ConstExpParser(tokens, symbolTable).parseConstExp());
    }
}
