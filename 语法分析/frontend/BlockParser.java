package frontend;

import java.util.ArrayList;

public class BlockParser {
    private ArrayList<Token> tokens;

    public BlockParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Block parseBlock() {

        Token lBrace = tokens.remove(0);

        ArrayList<BlockItem> blockItems = new ArrayList<>();
        while (!tokens.get(0).getCode().equals(Code.RBRACE)) {
            blockItems.add(new BlockItemParser(tokens).parseBlockItem());
        }

        Token rBrace = tokens.remove(0);

        return new Block(lBrace, blockItems, rBrace);
    }
}
