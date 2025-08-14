package frontend.symbol.exp.expFactor;

import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class LOrExp {
    /*
     LAndExp | LOrExp '||' LAndExp =  LAndExp { '||' LAndExp }
     */
    private final String name = "<LOrExp>";
    private LAndExp lAndExp; // LAndExp
    private ArrayList<Token> ops;
    private ArrayList<LAndExp> lAndExps; // { '||' LAndExp }

    public LOrExp(LAndExp lAndExp, ArrayList<Token> ops, ArrayList<LAndExp> lAndExps) {
        this.lAndExp = lAndExp;
        this.ops = ops;
        this.lAndExps = lAndExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lAndExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.lAndExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings, String ifLabel, String elseLabel) {
        iType = "i32";
        String orLabel = "_" + cnt.use();
        String res = lAndExp.toInstructions(cnt, iType, strings, orLabel, ifLabel);
        for (int i = 0; i < this.ops.size(); i++) {
//            String left = res;
//            String tmp = "%_" + cnt.use();
//            // %9 = icmp ne i32 %8, %7
//            strings.add("   " + tmp + " = icmp ne " + iType + " " + left + ", " + 0 + "\n");
//            // br i1 %3, label %4, label %7
//            strings.add("   br i1 " + tmp + ", label %" + ifLabel + ", label %" + orLabel + "\n");

            strings.add(orLabel + ":" + "       ;orLabel\n");
            orLabel = "_" + cnt.use();
            res = lAndExps.get(i).toInstructions(cnt, iType, strings, orLabel, ifLabel);
//            res = "%_" + cnt.use();
//            // <result> = or <ty> <op1>, <op2>
//            strings.add("   " + res + " = or " + iType + " " + 0 + ", " + right + "\n");
//            Counter.put(res, iType);
        }
//        if (this.ops.isEmpty()) {
//            strings.add(orLabel + ":" + "       ;orLabel\n");
//            strings.add("   br label %" + elseLabel + "\n");
//        }
        strings.add(orLabel + ":" + "       ;orLabel\n");
        strings.add("   br label %" + elseLabel + "\n");
        return res;
    }
}
