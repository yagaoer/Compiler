package frontend.symbol.stmt.stmtFactor.stmtBlock;

import frontend.symbol.block.Block;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.BlockBlock;

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

    @Override
    public BlockBlock parseLLVMBlock() {
        return this.block.parseLLVMBlock();
    }
}
