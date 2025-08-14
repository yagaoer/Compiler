package llvm.llvmInstruction;

import frontend.symbol.exp.Exp;
import llvm.Counter;

import java.util.ArrayList;

public class ReturnInstruction implements LLVMInstruction {
    private Exp exp = null;
    private String iType = "i32";
    public ReturnInstruction(Exp exp) {
        this.exp = exp;
    }

    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        if (this.exp == null) {
            strings.add("   ret void" + "\n");
        } else {
            String ret = this.exp.toInstructions(cnt, iType, strings);
            strings.add("   ret " + iType + " " + ret + "\n");
        }
        return strings;
    }
}
