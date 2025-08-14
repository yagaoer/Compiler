package frontend.lexer;

public class BlockItem {
    private final String name = "<BlockItem>";
    private BlockItemFactor blockItemFactor;

    public BlockItem(BlockItemFactor blockItemFactor) {
        this.blockItemFactor = blockItemFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.blockItemFactor.toString());
        //sb.append(this.name).append("\n");
        return sb.toString();
    }

    public BlockItemFactor getBlockItemFactor() {
        return blockItemFactor;
    }
}
