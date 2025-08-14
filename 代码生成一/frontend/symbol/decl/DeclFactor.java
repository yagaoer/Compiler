package frontend.symbol.decl;

import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public interface DeclFactor {
    ArrayList<LLVMGlobal> parseLLVMGlobal();
    String toString();

    ArrayList<LLVMInstruction> parseLLVMInstructions();
}
