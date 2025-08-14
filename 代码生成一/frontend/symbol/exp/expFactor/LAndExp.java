package frontend.symbol.exp.expFactor;

import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class LAndExp {
    /*
    EqExp | LAndExp '&&' EqExp = EqExp { '&&' EqExp }
     */
    private final String name = "<LAndExp>";
    private EqExp eqExp; // EqExp
    private ArrayList<Token> ops;
    private ArrayList<EqExp> eqExps; // { '&&' EqExp }

    public LAndExp(EqExp eqExp, ArrayList<Token> ops, ArrayList<EqExp> eqExps) {
        this.eqExp = eqExp;
        this.ops = ops;
        this.eqExps = eqExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.eqExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.eqExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings, String orLabel, String ifLabel) {
        iType = "i32";
        String andLabel = "_" + cnt.use();
        String res = eqExp.toInstructions(cnt, iType, strings);
        for (int i = 0; i < this.ops.size(); i++) {
            String left = res;
            String tmp = "%_" + cnt.use();
            // %9 = icmp eq i32 %8, %7
            strings.add("   " + tmp + " = icmp eq " + iType + " " + left + ", " + 0 + "\n");
            // br i1 %3, label %4, label %7
            strings.add("   br i1 " + tmp + ", label %" + orLabel + ", label %" + andLabel + "\n");

            strings.add(andLabel + ":" + "       ;andLabel\n");
            andLabel = "_" + cnt.use();
            res = eqExps.get(i).toInstructions(cnt, iType, strings);
//            res = "%_" + cnt.use();
//            // <result> = and <ty> <op1>, <op2>
//            strings.add("   " + res + " = and " + iType + " " + left + ", " + right + "\n");
//            Counter.put(res, iType);
        }
        String left = res;
        String tmp = "%_" + cnt.use();
        // %9 = icmp eq i32 %8, %7
        strings.add("   " + tmp + " = icmp eq " + iType + " " + left + ", " + 0 + "\n");
        // br i1 %3, label %4, label %7
        strings.add("   br i1 " + tmp + ", label %" + orLabel + ", label %" + ifLabel + "\n");
        return res;
    }
}
