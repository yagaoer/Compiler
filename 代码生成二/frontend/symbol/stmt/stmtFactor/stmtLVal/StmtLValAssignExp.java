package frontend.symbol.stmt.stmtFactor.stmtLVal;

import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LVal;
import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.LLVMInstruction;
import llvm.llvmInstruction.LValExpInstruction;

import java.util.ArrayList;

public class StmtLValAssignExp implements StmtFactor {
    private LVal lval; //  LVal
    private Token assign; // '='
    private Exp exp; // Exp
    private Token semicn; // ';'

    public StmtLValAssignExp(LVal lval, Token assign, Exp exp, Token semicn) {
        this.lval = lval;
        this.assign = assign;
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lval.toString()).append(this.assign.toString())
                .append(this.exp.toString()).append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {

    }

    @Override
    public LLVMBlock parseLLVMBlock() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(new LValExpInstruction(lval, exp));
        return new InstructionsBlock(instructions);
    }

    public LLVMInstruction parseLLVMInstruction() {
        return new LValExpInstruction(lval, exp);
    }
}
