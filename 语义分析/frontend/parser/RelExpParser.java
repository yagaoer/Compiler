package frontend.parser;

import frontend.SymbolTable;
import frontend.lexer.AddExp;
import frontend.TokenCode;
import frontend.lexer.RelExp;
import frontend.lexer.Token;

import java.util.ArrayList;

public class RelExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public RelExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public RelExp parseRelExp() {

        AddExp addExp = new AddExpParser(tokens, symbolTable).parseAddExp();

        ArrayList<Token> ops = new ArrayList<>();
        ArrayList<AddExp> addExps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(TokenCode.LSS) || tokens.get(0).getCode().equals(TokenCode.GRE) ||
                tokens.get(0).getCode().equals(TokenCode.LEQ) || tokens.get(0).getCode().equals(TokenCode.GEQ)) {

            ops.add(tokens.remove(0));

            addExps.add(new AddExpParser(tokens, symbolTable).parseAddExp());
        }

        return new RelExp(addExp, ops, addExps);
    }
}
