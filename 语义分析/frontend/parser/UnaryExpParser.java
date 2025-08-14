package frontend.parser;

import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.Token;
import frontend.lexer.UnaryExp;

import java.util.ArrayList;

public class UnaryExpParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public UnaryExpParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public UnaryExp parseUnaryExp() {

        if (tokens.get(0).getCode().equals(TokenCode.IDENFR) &&
                tokens.get(1).getCode().equals(TokenCode.LPARENT)) {
            return new UnaryExp(new UnaryExpFuncRParser(tokens, symbolTable).parseUnaryFuncRExp());
        }

        if (tokens.get(0).getCode().equals(TokenCode.PLUS) ||
                tokens.get(0).getCode().equals(TokenCode.MINU) ||
                tokens.get(0).getCode().equals(TokenCode.NOT)) {
            return new UnaryExp(new UnaryExpOpParser(tokens, symbolTable).parseUnaryExpOp());
        }

        return new UnaryExp(new PrimaryExpParser(tokens, symbolTable).parsePrimaryExp());
    }
}
