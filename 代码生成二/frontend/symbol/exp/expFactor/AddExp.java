package frontend.symbol.exp.expFactor;

import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class AddExp {
    /*
      MulExp | AddExp ('+' | '−') MulExp ==  MulExp { ('+' | '−') MulExp }
     */
    private final String name = "<AddExp>";
    private MulExp mulExp; // MulExp
    private ArrayList<Token> ops;
    private ArrayList<MulExp> mulExps; // { ('+' | '−') MulExp }
    private SymbolTable symbolTable;

    public AddExp(MulExp mulExp, ArrayList<Token> ops, ArrayList<MulExp> mulExps, SymbolTable symbolTable) {
        this.mulExp = mulExp;
        this.ops = ops;
        this.mulExps = mulExps;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mulExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.mulExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return this.mulExp.getSymbolCode();
    }

    public int toRes() {
        int res = mulExp.toRes();
        for (int i = 0; i < this.ops.size(); i++) {
            if (this.ops.get(i).getCode().equals(TokenCode.PLUS)) {
                res += this.mulExps.get(i).toRes();
            } else {
                res -= this.mulExps.get(i).toRes();
            }
        }
        return res;
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        iType = "i32";
        String res = mulExp.toInstructions(cnt, iType, strings);
//        System.out.print(cnt + res + "\n");
        for (int i = 0; i < this.ops.size(); i++) {
            String left = res;
            if (this.ops.get(i).getCode().equals(TokenCode.PLUS)) {
                String right = mulExps.get(i).toInstructions(cnt, iType, strings);
                res = "%_" + cnt.use();
                // %9 = add i32 %5, %8
                strings.add("   " + res + " = add " + iType + " " + left + ", " + right + "\n");
            } else {
                String right = mulExps.get(i).toInstructions(cnt, iType, strings);
                res = "%_" + cnt.use();
                // %3 = sub i32 %2, %1
                strings.add("   " + res + " = sub " + iType + " " + left + ", " + right + "\n");
            }
            Counter.put(res, iType);
        }
        return res;
    }
}
