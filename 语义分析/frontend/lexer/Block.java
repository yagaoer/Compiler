package frontend.lexer;

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

    public ArrayList<BlockItem> getBlockItems() {
        return blockItems;
    }

    public void fError() {
        if (blockItems.isEmpty()) {
            return;
        }
        for (BlockItem blockItem : blockItems) {
            blockItem.getBlockItemFactor().fError();
        }
    }

    public void gError() {
        if (blockItems.isEmpty()) {
            new Token("g", rBrace.getLine());
        } else {
            BlockItemFactor blockItemFactor = blockItems.get(blockItems.size() - 1).getBlockItemFactor();
            if (blockItemFactor instanceof Stmt) {
                StmtFactor stmtFactor = ((Stmt) blockItemFactor).getStmtFactor();
                if (stmtFactor instanceof StmtReturn) {
                    return;
                }
            }
            new Token("g", rBrace.getLine());
        }
    }
}
