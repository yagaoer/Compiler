package frontend.symbol.block;

import frontend.symbol.SymbolTable;

public class BlockItem {
    private final String name = "<BlockItem>";
    private BlockItemFactor blockItemFactor;
    private SymbolTable symbolTable;

    public BlockItem(BlockItemFactor blockItemFactor, SymbolTable symbolTable) {
        this.blockItemFactor = blockItemFactor;
        this.symbolTable = symbolTable;
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
