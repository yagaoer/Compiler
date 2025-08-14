package frontend.symbol.decl.varDecl.varDef;

import frontend.symbol.BType;
import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

public interface VarDefFactor {
    LLVMGlobal parseLLVMGlobal(BType bType);

    LLVMInstruction LLVMInstruction(BType btype);
}
