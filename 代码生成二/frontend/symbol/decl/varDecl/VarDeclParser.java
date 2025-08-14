package frontend.symbol.decl.varDecl;

import frontend.lexer.TokenCode;
import frontend.symbol.BTypeParser;
import frontend.symbol.BType;
import frontend.lexer.Token;
import frontend.symbol.SymbolTable;
import frontend.symbol.decl.varDecl.varDef.VarDef;
import frontend.symbol.decl.varDecl.varDef.VarDefParser;

import java.util.ArrayList;

public class VarDeclParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public VarDeclParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public VarDecl parseVarDecl() {

        BType btype = new BTypeParser(tokens).parseBtype();

        VarDef varDef = new VarDefParser(tokens, symbolTable, btype).parseVarDef();

        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<VarDef> varDefs = new ArrayList<>();
        while (tokens.get(0).getCode().equals(TokenCode.COMMA)) {

            commas.add(tokens.remove(0));

            varDefs.add(new VarDefParser(tokens, symbolTable, btype).parseVarDef());
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        return new VarDecl(btype, varDef, commas, varDefs, semicn, symbolTable);
    }
}
