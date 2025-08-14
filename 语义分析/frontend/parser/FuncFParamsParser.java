package frontend.parser;

import frontend.Symbol;
import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.FuncFParam;
import frontend.lexer.FuncFParams;
import frontend.lexer.Token;

import java.util.ArrayList;

public class FuncFParamsParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;
    private ArrayList<Symbol> symbols;

    public FuncFParamsParser(ArrayList<Token> tokens, SymbolTable symbolTable, ArrayList<Symbol> symbols) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
        this.symbols = symbols;
    }

    public FuncFParams parseFuncFParams() {

        FuncFParam funcFParam = new FuncFParamParser(tokens, symbolTable, symbols).parseFuncFParam();

        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<FuncFParam> funcFParams = new ArrayList<>();
        while (tokens.get(0).getCode().equals(TokenCode.COMMA)) {

            commas.add(tokens.remove(0));

            funcFParams.add(new FuncFParamParser(tokens, symbolTable, symbols).parseFuncFParam());
        }

        return new FuncFParams(funcFParam, commas, funcFParams);
    }
}
