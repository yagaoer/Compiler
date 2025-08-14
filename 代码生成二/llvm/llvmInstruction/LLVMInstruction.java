package llvm.llvmInstruction;

import llvm.Counter;

import java.util.ArrayList;
import java.util.Collection;

public interface LLVMInstruction {
    ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel);
}
