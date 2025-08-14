package frontend.parser;

import frontend.SymbolTable;
import frontend.lexer.BlockItem;
import frontend.TokenCode;
import frontend.lexer.Token;

import java.util.ArrayList;

public class BlockItemParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public BlockItemParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public BlockItem parseBlockItem() {

        if (tokens.get(0).getCode().equals(TokenCode.CONSTTK) || tokens.get(0).getCode().equals(TokenCode.INTTK)
               || tokens.get(0).getCode().equals(TokenCode.CHARTK)) {
            return new BlockItem(new DeclParser(tokens, symbolTable).parseDecl());
        }

        return new BlockItem(new StmtParser(tokens, symbolTable).parseStmt());
    }
}
