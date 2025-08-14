package llvm.llvmInstruction;

import llvm.Counter;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class ContinueInstruction implements LLVMInstruction {
    @Override
    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        // br label %1
        strings.add("   br label %" + continueLabel + "\n");
        return strings;
    }
}
