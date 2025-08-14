package frontend.parser;

import frontend.*;
import frontend.lexer.BType;
import frontend.lexer.ConstDecl;
import frontend.lexer.ConstDef;
import frontend.lexer.Token;

import java.util.ArrayList;

public class ConstDeclParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public ConstDeclParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public ConstDecl parseConstDecl() {
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<ConstDef> constDefs = new ArrayList<>();

        Token constTk = tokens.remove(0);

        BType btype = new BTypeParser(tokens).parseBtype();

        ConstDef constDef = new ConstDefParser(tokens, symbolTable, btype).parseConstDef();

        while (tokens.get(0).getCode().equals(TokenCode.COMMA)) {

            commas.add(tokens.remove(0));

            constDefs.add(new ConstDefParser(tokens, symbolTable, btype).parseConstDef());
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        return new ConstDecl(constTk, btype, constDef, commas, constDefs, semicn);
    }

}
