package frontend.symbol.exp.expFactor;

import frontend.lexer.TokenCode;
import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class EqExp {
    /*
     RelExp | EqExp ('==' | '!=') RelExp = RelExp { ('==' | '!=') RelExp }
     */
    private final String name = "<EqExp>";
    private RelExp relExp; // RelExp
    private ArrayList<Token> ops;
    private ArrayList<RelExp> relExps; // { ('==' | '!=') RelExp }

    public EqExp(RelExp relExp, ArrayList<Token> ops, ArrayList<RelExp> relExps) {
        this.relExp = relExp;
        this.ops = ops;
        this.relExps = relExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.relExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.relExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        iType = "i32";
        String res = relExp.toInstructions(cnt, iType, strings);
        for (int i = 0; i < this.ops.size(); i++) {
            String left = res;
            if (this.ops.get(i).getCode().equals(TokenCode.EQL)) {
                String right = relExps.get(i).toInstructions(cnt, iType, strings);
                String tmp = "%_" + cnt.use();
                res = "%_" + cnt.use();
                // %24 = icmp eq i32 %23, %22
                strings.add("   " + tmp + " = icmp eq " + iType + " " + left + ", " + right + "\n");
                // %8 = zext i8 %7 to i32
                strings.add("   " + res + " = zext i1 " + tmp + " to " + iType + "\n");
            } else {
                String right = relExps.get(i).toInstructions(cnt, iType, strings);
                String tmp = "%_" + cnt.use();
                res = "%_" + cnt.use();
                // %9 = icmp ne i32 %8, %7
                strings.add("   " + tmp + " = icmp ne " + iType + " " + left + ", " + right + "\n");
                // %8 = zext i8 %7 to i32
                strings.add("   " + res + " = zext i1 " + tmp + " to " + iType + "\n");
            }
            Counter.put(res, iType);
        }
        return res;
    }
}
