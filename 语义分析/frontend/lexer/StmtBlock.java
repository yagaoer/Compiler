package frontend.lexer;

public class StmtBlock implements StmtFactor {
    /*
    Block
     */
    private Block block;

    public StmtBlock(Block block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return this.block.toString();
    }

    @Override
    public void fError() {
        this.block.fError();
    }
}
