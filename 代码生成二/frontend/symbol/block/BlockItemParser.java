package frontend.symbol.block;

import frontend.symbol.decl.DeclParser;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;
import frontend.symbol.stmt.StmtParser;

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
            return new BlockItem(new DeclParser(tokens, symbolTable).parseDecl(), symbolTable);
        }

        return new BlockItem(new StmtParser(tokens, symbolTable).parseStmt(), symbolTable);
    }
}
