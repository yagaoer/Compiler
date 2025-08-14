package frontend.symbol.decl.constDecl.constDef.constInitVal;

import frontend.symbol.exp.ConstExpParser;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.symbol.exp.ConstExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class ConstInitValArrayParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public ConstInitValArrayParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public ConstInitValArray parseConstInitValArray() {

        Token lBrace = tokens.remove(0);


        ConstExp constExp = null;
        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<ConstExp> constExps = new ArrayList<>();

        if (tokens.get(0).getCode().equals(TokenCode.IDENFR) ||
                tokens.get(0).getCode().equals(TokenCode.INTCON) ||
                tokens.get(0).getCode().equals(TokenCode.CHRCON) ||
                tokens.get(0).getCode().equals(TokenCode.LPARENT) ||
                tokens.get(0).getCode().equals(TokenCode.PLUS) ||
                tokens.get(0).getCode().equals(TokenCode.MINU) ||
                tokens.get(0).getCode().equals(TokenCode.NOT)) {

            constExp = new ConstExpParser(tokens, symbolTable).parseConstExp();
            while (tokens.get(0).getCode().equals(TokenCode.COMMA)) {

                commas.add(tokens.remove(0));

                constExps.add(new ConstExpParser(tokens, symbolTable).parseConstExp());
            }
        }

        Token rBrace = tokens.remove(0);

        return new ConstInitValArray(lBrace, constExp, commas, constExps, rBrace);
    }
}
