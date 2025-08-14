package frontend.parser;

import frontend.SymbolTable;
import frontend.lexer.Block;
import frontend.lexer.BlockItem;
import frontend.TokenCode;
import frontend.lexer.Token;

import java.util.ArrayList;

public class BlockParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public BlockParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable ;
    }

    public Block parseBlock() {

        Token lBrace = tokens.remove(0);

        ArrayList<BlockItem> blockItems = new ArrayList<>();
        while (!tokens.get(0).getCode().equals(TokenCode.RBRACE)) {
            blockItems.add(new BlockItemParser(tokens, symbolTable).parseBlockItem());
        }

        Token rBrace = tokens.remove(0);

        return new Block(lBrace, blockItems, rBrace);
    }
}
