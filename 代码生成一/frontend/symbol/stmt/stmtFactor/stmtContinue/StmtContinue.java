package frontend.symbol.stmt.stmtFactor.stmtContinue;

import frontend.lexer.Token;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.ContinueInstruction;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class StmtContinue implements StmtFactor {
    private Token continueTk; // 'continue'
    private Token semicn; // ';'

    public StmtContinue(Token continueTk, Token semicn) {
        this.continueTk = continueTk;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.continueTk.toString()).append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {

    }

    @Override
    public LLVMBlock parseLLVMBlock() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(new ContinueInstruction());
        return new InstructionsBlock(instructions);
    }

    public ContinueInstruction parseLLVMInstruction() {
        return new ContinueInstruction();
    }
}
