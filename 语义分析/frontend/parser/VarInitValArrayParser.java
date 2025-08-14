package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.Exp;
import frontend.lexer.Token;
import frontend.lexer.VarInitValArray;

import java.util.ArrayList;

public class VarInitValArrayParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public VarInitValArrayParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public VarInitValArray parseVarInitValArray() {
        Token lBrace = tokens.remove(0);


        Exp exp = null;
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<Exp> exps = new ArrayList<>();

        if (tokens.get(0).getCode().equals(TokenCode.IDENFR) ||
                tokens.get(0).getCode().equals(TokenCode.INTCON) ||
                tokens.get(0).getCode().equals(TokenCode.CHRCON) ||
                tokens.get(0).getCode().equals(TokenCode.LPARENT) ||
                tokens.get(0).getCode().equals(TokenCode.PLUS) ||
                tokens.get(0).getCode().equals(TokenCode.MINU) ||
                tokens.get(0).getCode().equals(TokenCode.NOT)) {

            exp = new ExpParser(tokens, symbolTable).parseExp();

            while (tokens.get(0).getCode().equals(TokenCode.COMMA)) {

                commas.add(tokens.remove(0));

                exps.add(new ExpParser(tokens, symbolTable).parseExp());
            }
        }

        Token rBrace = tokens.remove(0);

        return new VarInitValArray(lBrace, exp, commas, exps, rBrace);
    }
}
