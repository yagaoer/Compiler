package frontend.symbol.stmt.stmtFactor.stmtReturn;

import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.LLVMInstruction;
import llvm.llvmInstruction.ReturnInstruction;

import java.util.ArrayList;

public class StmtReturn implements StmtFactor {
    /*
    'return' [Exp] ';'
     */
    private Token returnTk; // 'return'
    private Exp exp; // [Exp]
    private Token semicn; // ';'

    public StmtReturn(Token returnTk, Exp exp, Token semicn) {
        this.returnTk = returnTk;
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.returnTk.toString());
        if (this.exp != null) {
            sb.append(this.exp.toString());
        }
        sb.append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {
        if (this.exp != null) {
            new Token("f", this.returnTk.getLine());
        }
    }

    @Override
    public LLVMBlock parseLLVMBlock() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(new ReturnInstruction(exp));
        return new InstructionsBlock(instructions);
    }

    public ReturnInstruction parseLLVMInstructions() {
        return new ReturnInstruction(exp);
    }
}
