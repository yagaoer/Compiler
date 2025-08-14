package llvm.llvmInstruction;

import frontend.symbol.exp.Exp;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LVal;
import llvm.Counter;

import java.util.ArrayList;

public class LValExpInstruction implements LLVMInstruction {
    private LVal lval = null; //  LVal
    private Exp exp; // Exp
    private String iType = "i32";

    // LVal = Exp
    public LValExpInstruction(LVal lval, Exp exp) {
        this.lval = lval;
        this.exp = exp;
    }

    // [Exp] ;
    public LValExpInstruction(Exp exp) {
        this.exp = exp;
    }

    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        String res = "";
        if (exp != null) {
            res = exp.toInstructions(cnt, iType, strings);
        }
        if (lval != null) {
            ArrayList<String> ret = lval.toPtrInstructions(cnt, iType, strings);
            String ptrType = ret.get(0);
            String ptr = ret.get(1);
            if (Counter.getType(res).equals("i32") && ptrType.equals("i8*")) {
                String tmp = "%_" + cnt.use();
                // %10 = trunc i32 %9 to i8
                strings.add("   " + tmp + " = trunc i32 " + res + " to i8\n");
                Counter.put(tmp, "i8");
                res = tmp;
            }
            // store i32 %3, i32* %1
            strings.add("   store " + Counter.getType(res) + " " + res + ", " + ptrType + " "+ ptr + "\n");
        }
        return strings;
    }
}
