package frontend.symbol.decl.varDecl.varDef.varInitVal;

import frontend.symbol.exp.ExpParser;
import frontend.symbol.decl.StringConstParser;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;

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
