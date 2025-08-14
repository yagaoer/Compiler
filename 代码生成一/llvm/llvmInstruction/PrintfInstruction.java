package llvm.llvmInstruction;

import frontend.symbol.exp.Exp;
import frontend.symbol.decl.StringConst;
import llvm.Counter;

import java.util.ArrayList;

public class PrintfInstruction implements LLVMInstruction {
    private StringConst stringConst; // StringConst
    private ArrayList<Exp> exps; // {','Exp}
    private String iType = "i32";

    public PrintfInstruction(StringConst stringConst, ArrayList<Exp> exps) {
        this.stringConst = stringConst;
        this.exps = exps;
    }

    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        char[] chars = stringConst.getFormatCharArray();
        int k = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '%') {
                i++;
                if (chars[i] == 'd') {
                    // call void @putint(i32 %7)
                    strings.add("   call void @putint(" + iType + " " +
                            exps.get(k++).toInstructions(cnt, iType, strings) + ")" + "\n");
                } else if (chars[i] == 'c') {
                    // call void @putch(i32 %7)
                    strings.add("   call void @putch(" + iType + " " +
                            exps.get(k++).toInstructions(cnt, iType, strings) + ")" + "\n");
                } else {
                    i--;
                    // call void @putch(i32 %7)
                    strings.add("   call void @putch(" + iType + " " + (int)chars[i] + ")" + "      ;" + chars[i] + "\n");
                }
            } else {
                // call void @putch(i32 %7)
                strings.add("   call void @putch(" + iType + " " + (int)chars[i] + ")" + "      ;" + chars[i] + "\n");
            }
        }
//        strings.add("   call void @putch(" + iType + " " + 0 + ")" + "      ;" + '\0' + "\n");
        return strings;
    }
}
