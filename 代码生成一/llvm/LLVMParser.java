package llvm;

import frontend.symbol.CompUnit;
import frontend.symbol.decl.Decl;
import frontend.symbol.func.FuncDef;

public class LLVMParser {
    private CompUnit compUnit;

    public LLVMParser(CompUnit compUnit) {
        this.compUnit = compUnit;
    }

    public LLVMModule parseLLVMModule() {
        LLVMModule llvmModule = new LLVMModule();
        // 全局变量
        for (Decl decl : this.compUnit.getDecls()) {
            llvmModule.addGlobals(decl.parseLLVMGlobal());
        }
        // 函数
        for (FuncDef funcDef : this.compUnit.getFuncDefs()) {
            llvmModule.addFunc(funcDef.parseLLVMFunc());
        }
        // Main
        llvmModule.addFunc(this.compUnit.getMainFuncDef().parseLLVMFunc());
        return llvmModule;
    }
}
