package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp;

import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.CharacterParser;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LValParser;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.NumberParser;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.PrimaryExpExpParser;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;

import java.util.ArrayList;

public class PrimaryExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public PrimaryExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public PrimaryExp parsePrimaryExp() {

        if (tokens.get(0).getCode().equals(TokenCode.LPARENT)) {
           return new PrimaryExp(new PrimaryExpExpParser(tokens, symbolTable).parsePrimaryExpExp());
        }
        if (tokens.get(0).getCode().equals(TokenCode.IDENFR)) {
            return new PrimaryExp(new LValParser(tokens, symbolTable).parseLVal());
        }
        if (tokens.get(0).getCode().equals(TokenCode.INTCON)) {
            return new PrimaryExp(new NumberParser(tokens).parseNumber());
        }
        return new PrimaryExp(new CharacterParser(tokens).parseCharacter());
    }
}
