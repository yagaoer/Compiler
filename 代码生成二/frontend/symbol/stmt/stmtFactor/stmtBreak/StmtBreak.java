package frontend.symbol.stmt.stmtFactor.stmtBreak;

import frontend.lexer.Token;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.BreakInstruction;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class StmtBreak implements StmtFactor {
    private Token breakTk; // 'break'
    private Token semicn; // ';'

    public StmtBreak(Token breakTk, Token semicn) {
        this.breakTk = breakTk;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.breakTk.toString()).append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {
    }


    @Override
    public LLVMBlock parseLLVMBlock() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(new BreakInstruction());
        return new InstructionsBlock(instructions);
    }

    public BreakInstruction parseLLVMInstruction() {
        return new BreakInstruction();
    }
}
