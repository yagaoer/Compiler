package frontend.symbol.stmt.stmtFactor.stmtExp;

import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.LLVMInstruction;
import llvm.llvmInstruction.LValExpInstruction;

import java.util.ArrayList;

public class StmtExp implements StmtFactor {
    /*
    [Exp] ';'
     */
    private Exp exp;
    private Token semicn; // ';'

    public StmtExp(Exp exp, Token semicn) {
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.exp != null) {
            sb.append(this.exp.toString());
        }
        sb.append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {

    }

    @Override
    public LLVMBlock parseLLVMBlock() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(new LValExpInstruction(exp));
        return new InstructionsBlock(instructions);
    }

    public LLVMInstruction parseLLVMInstruction() {
        return new LValExpInstruction(exp);
    }
}
