package frontend.symbol.decl;

import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.symbol.decl.Decl;
import frontend.lexer.Token;
import frontend.symbol.decl.constDecl.ConstDeclParser;
import frontend.symbol.decl.varDecl.VarDeclParser;

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
