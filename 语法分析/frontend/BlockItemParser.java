package frontend;

import java.util.ArrayList;

public class BlockItemParser {
    private ArrayList<Token> tokens;

    public BlockItemParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public BlockItem parseBlockItem() {

        if (tokens.get(0).getCode().equals(Code.CONSTTK) || tokens.get(0).getCode().equals(Code.INTTK)
               || tokens.get(0).getCode().equals(Code.CHARTK)) {
            return new BlockItem(new DeclParser(tokens).parseDecl());
        }

        return new BlockItem(new StmtParser(tokens).parseStmt());
    }
}
