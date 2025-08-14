package frontend.symbol.stmt.stmtFactor.stmtLVal;

import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LVal;
import frontend.lexer.Token;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.GetFuncInstruction;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class StmtLValAssignGetInt implements StmtFactor {
    /*
     LVal '=' 'getint''('')'';'
     */
    private LVal lval; //  LVal
    private Token assign; // '='
    private Token getInt; // 'getint'
    private Token lParent; // '('
    private Token rParent; // ')'
    private Token semicn; // ';'

    public StmtLValAssignGetInt(LVal lval, Token assign, Token getInt, Token lParent, Token rParent, Token semicn) {
        this.lval = lval;
        this.assign = assign;
        this.getInt = getInt;
        this.lParent = lParent;
        this.rParent = rParent;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lval.toString()).append(this.assign.toString()).append(this.getInt.toString())
                .append(this.lParent.toString()).append(this.rParent.toString())
                .append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {

    }

    @Override
    public LLVMBlock parseLLVMBlock() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(new GetFuncInstruction(lval, getInt));
        return new InstructionsBlock(instructions);
    }

    public GetFuncInstruction parseLLVMInstruction() {
        return new GetFuncInstruction(lval, getInt);
    }
}
