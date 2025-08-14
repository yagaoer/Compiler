package frontend.symbol.exp.expFactor;

import frontend.lexer.TokenCode;
import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class RelExp {
    /*
     AddExp | RelExp ('<' | '>' | '<=' | '>=') AddExp = AddExp { ('<' | '>' | '<=' | '>=') AddExp }
     */
    private final String name = "<RelExp>";
    private AddExp addExp; // AddExp
    private ArrayList<Token> ops;
    private ArrayList<AddExp> addExps; // { ('+' | '−') AddExp }

    public RelExp(AddExp addExp, ArrayList<Token> ops, ArrayList<AddExp> addExps) {
        this.addExp = addExp;
        this.ops = ops;
        this.addExps = addExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.addExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        iType = "i32";
        String res = addExp.toInstructions(cnt, iType, strings);
        for (int i = 0; i < this.ops.size(); i++) {
            String left = res;
            if (this.ops.get(i).getCode().equals(TokenCode.GRE)) {
                String right = addExps.get(i).toInstructions(cnt, iType, strings);
                String tmp = "%_" + cnt.use();
                res = "%_" + cnt.use();
                // %24 = icmp sgt i32 %23, %22
                strings.add("   " + tmp + " = icmp sgt " + iType + " " + left + ", " + right + "\n");
                // %8 = zext i8 %7 to i32
                strings.add("   " + res + " = zext i1 " + tmp + " to " + iType + "\n");
            } else if (this.ops.get(i).getCode().equals(TokenCode.LSS)) {
                String right = addExps.get(i).toInstructions(cnt, iType, strings);
                String tmp = "%_" + cnt.use();
                res = "%_" + cnt.use();
                // %9 = icmp slt i32 %8, %7
                strings.add("   " + tmp + " = icmp slt " + iType + " " + left + ", " + right + "\n");
                // %8 = zext i8 %7 to i32
                strings.add("   " + res + " = zext i1 " + tmp + " to " + iType + "\n");
            } else if (this.ops.get(i).getCode().equals(TokenCode.LEQ)) {
                String right = addExps.get(i).toInstructions(cnt, iType, strings);
                String tmp = "%_" + cnt.use();
                res = "%_" + cnt.use();
                // %9 = icmp sle i32 %8, %7
                strings.add("   " + tmp + " = icmp sle " + iType + " " + left + ", " + right + "\n");
                // %8 = zext i8 %7 to i32
                strings.add("   " + res + " = zext i1 " + tmp + " to " + iType + "\n");
            } else {
                String right = addExps.get(i).toInstructions(cnt, iType, strings);
                String tmp = "%_" + cnt.use();
                res = "%_" + cnt.use();
                // %9 = icmp sge i32 %8, %7
                strings.add("   " + tmp + " = icmp sge " + iType + " " + left + ", " + right + "\n");
                // %8 = zext i8 %7 to i32
                strings.add("   " + res + " = zext i1 " + tmp + " to " + iType + "\n");
            }
            Counter.put(res, iType);
        }
        return res;
    }
}
