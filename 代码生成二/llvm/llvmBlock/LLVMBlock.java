package llvm.llvmBlock;

import llvm.Counter;

import java.util.ArrayList;

public interface LLVMBlock {
    ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel);
}
