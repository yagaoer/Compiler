package llvm.llvmInstruction;

import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LVal;
import frontend.lexer.Token;
import llvm.Counter;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class GetFuncInstruction implements LLVMInstruction {
    private LVal lval; //  LVal
    private Token getFunc; // 'getint' | 'getchar'
    private String iType = "i32";

    public GetFuncInstruction(LVal lval, Token getInt) {
        this.lval = lval;
        this.getFunc = getInt;
    }

    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        // %3 = call i32 @getint()
        String res = "%_" + cnt.use();
        strings.add("   " + res + " = call " + iType + " @" + getFunc.getName() + "()" + "\n");
        Counter.put(res, "i32");
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
        strings.add("   store " + Counter.getType(res) + " " + res + ", " + ptrType + " " + ptr + "\n");
        return strings;
    }
}
