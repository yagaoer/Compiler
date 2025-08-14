package llvm.llvmBlock;

import llvm.Counter;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class InstructionsBlock implements LLVMBlock {
    private ArrayList<LLVMInstruction> instructions;

    public InstructionsBlock(ArrayList<LLVMInstruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < instructions.size(); i++) {
            strings.addAll(instructions.get(i).getString(cnt, breakLabel, continueLabel));
        }
        return strings;
    }
}
