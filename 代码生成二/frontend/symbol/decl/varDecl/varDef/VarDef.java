package frontend.symbol.decl.varDecl.varDef;

import frontend.symbol.BType;
import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

public class VarDef {
    private final String name = "<VarDef>";
    private VarDefFactor varDefFactor;

    public VarDef(VarDefFactor varDefFactor) {
        this.varDefFactor = varDefFactor;
    }

    public LLVMGlobal parseLLVMGlobal(BType bType) {
        return this.varDefFactor.parseLLVMGlobal(bType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.varDefFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public LLVMInstruction parseLLVMInstruction(BType btype) {
        return this.varDefFactor.LLVMInstruction(btype);
    }
}
