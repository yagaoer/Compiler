package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.Exp;
import frontend.lexer.FuncRParams;
import frontend.lexer.Token;

import java.util.ArrayList;

public class FuncRParamsParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public FuncRParamsParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public FuncRParams parseFuncRParams() {

        Exp exp = new ExpParser(tokens, symbolTable).parseExp();

        ArrayList<Token> commas = new ArrayList<>();

        ArrayList<Exp> exps = new ArrayList<>();

        while (tokens.get(0).getCode().equals(TokenCode.COMMA)) {

            commas.add(tokens.remove(0));

            exps.add(new ExpParser(tokens, symbolTable).parseExp());
        }

        return new FuncRParams(exp, commas, exps);
    }
}
