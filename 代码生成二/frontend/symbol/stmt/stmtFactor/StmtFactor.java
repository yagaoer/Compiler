package frontend.symbol.stmt.stmtFactor;

import llvm.llvmBlock.LLVMBlock;

public interface StmtFactor {
    void fError();

    LLVMBlock parseLLVMBlock();
}
