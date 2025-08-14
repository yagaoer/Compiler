package llvm;

import java.util.ArrayList;

public class LLVMModule {
    private ArrayList<LLVMGlobal> llvmGlobals;
    private ArrayList<LLVMFunc> llvmFuncs;

    public LLVMModule() {
        this.llvmFuncs = new ArrayList<>();
        this.llvmGlobals = new ArrayList<>();
    }

    public void addFunc(LLVMFunc function) {
        this.llvmFuncs.add(function);
    }

    public void addGlobals(ArrayList<LLVMGlobal> globals) {
        this.llvmGlobals.addAll(globals);
    }

    public String getString() {
        Counter cnt = new Counter();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("declare i32 @getint()\n" +
                "declare i32 @getchar()\n" +
                "declare void @putint(i32)\n" +
                "declare void @putch(i32)\n" +
                "declare void @putstr(i8*)\n" + "\n");
        for (LLVMGlobal llvmGlobal : llvmGlobals) {
            strings.addAll(llvmGlobal.getString());
        }
        strings.add("\n");
        for (LLVMFunc llvmFunc : llvmFuncs) {
            strings.addAll(llvmFunc.getString(cnt));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i));
        }
        return sb.toString();
    }
}
