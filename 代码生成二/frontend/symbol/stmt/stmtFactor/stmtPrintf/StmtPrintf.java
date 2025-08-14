package frontend.symbol.stmt.stmtFactor.stmtPrintf;

import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import frontend.symbol.decl.StringConst;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.LLVMInstruction;
import llvm.llvmInstruction.PrintfInstruction;

import java.util.ArrayList;

public class StmtPrintf implements StmtFactor {
    /*
     'printf''('StringConst {','Exp}')'';'
     */
    private Token printf; // 'printf'
    private Token lParent; // '('
    private StringConst stringConst; // StringConst
    private ArrayList<Token> commmas;
    private ArrayList<Exp> exps; // {','Exp}
    private Token rParent; // ')'
    private Token semicn; // ';'

    public StmtPrintf(Token printf, Token lParent, StringConst stringConst, ArrayList<Token> commas,
                     ArrayList<Exp> exps, Token rParent, Token semicn) {
        this.printf = printf;
        this.lParent = lParent;
        this.stringConst = stringConst;
        this.commmas = commas;
        this.exps = exps;
        this.rParent = rParent;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.printf.toString()).append(this.lParent.toString()).append(this.stringConst.toString());
        if (this.commmas != null && this.exps != null &&
                this.commmas.size() == this.exps.size()) {
            for (int i = 0; i < this.commmas.size(); i++) {
                sb.append(this.commmas.get(i).toString()).append(this.exps.get(i).toString());
            }
        }
        sb.append(this.rParent.toString()).append(this.semicn.toString());
        return sb.toString();
    }

    @Override
    public void fError() {

    }

    @Override
    public LLVMBlock parseLLVMBlock() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(new PrintfInstruction(stringConst, exps));
        return new InstructionsBlock(instructions);
    }

    public PrintfInstruction parseLLVMInstructions() {
        return new PrintfInstruction(stringConst, exps);
    }
}
