package frontend;

import java.util.ArrayList;

public class Block {
    private final String name = "<Block>";
    private Token lBrace; // '{'
    private ArrayList<BlockItem> blockItems; // { BlockItem }
    private Token rBrace; // '}'

    public Block(Token lBrace, ArrayList<BlockItem> blockItems, Token rBrace) {
        this.lBrace = lBrace;
        this.blockItems = blockItems;
        this.rBrace = rBrace;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lBrace.toString());
        if (this.blockItems != null) {
            for (int i = 0; i < this.blockItems.size(); i++) {
                sb.append(this.blockItems.get(i).toString());
            }
        }
        sb.append(this.rBrace.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
