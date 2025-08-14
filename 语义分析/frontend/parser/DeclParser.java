package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.Decl;
import frontend.lexer.Token;

import java.util.ArrayList;

public class DeclParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public DeclParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public Decl parseDecl() {
        if (tokens.get(0).getCode().equals(TokenCode.CONSTTK)) {
            return new Decl(new ConstDeclParser(tokens, symbolTable).parseConstDecl());
        }
        return new Decl(new VarDeclParser(tokens, symbolTable).parseVarDecl());
    }
}
