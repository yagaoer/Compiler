package frontend.symbol.exp.expFactor;

import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.Token;
import frontend.symbol.exp.expFactor.unaryExp.UnaryExp;
import llvm.Counter;

import java.util.ArrayList;

public class MulExp {
    /*
     UnaryExp | MulExp ('*' | '/' | '%') UnaryExp == UnaryExp {('*' | '/' | '%') UnaryExp}
     */
    private final String name = "<MulExp>";
    private UnaryExp unaryExp; // UnaryExp
    private ArrayList<Token> ops;
    private ArrayList<UnaryExp> unaryExps; // { ('*' | '/' | '%') UnaryExp }
    private SymbolTable symbolTable;

    public MulExp(UnaryExp unaryExp, ArrayList<Token> ops, ArrayList<UnaryExp> unaryExps, SymbolTable symbolTable) {
        this.unaryExp = unaryExp;
        this.ops = ops;
        this.unaryExps = unaryExps;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(unaryExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < ops.size(); i++) {
            sb.append(ops.get(i).toString()).append(unaryExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return this.unaryExp.getSymbolCode();
    }

    public int toRes() {
        int res = unaryExp.toRes();
        for (int i = 0; i < this.ops.size(); i++) {
            if (this.ops.get(i).getCode().equals(TokenCode.MULT)) {
                res *= this.unaryExps.get(i).toRes();
            } else if (this.ops.get(i).getCode().equals(TokenCode.DIV)) {
                res /= this.unaryExps.get(i).toRes();
            } else {
                res %= this.unaryExps.get(i).toRes();
            }
        }
        return res;
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        iType = "i32";
        String res = unaryExp.toInstructions(cnt, iType, strings);
        for (int i = 0; i < this.ops.size(); i++) {
            String left = res;
            if (this.ops.get(i).getCode().equals(TokenCode.MULT)) {
                String right = unaryExps.get(i).toInstructions(cnt, iType, strings);
                res = "%_" + cnt.use();
                // %3 = mul i32 %1, %2
                strings.add("   " + res + " = mul " + iType + " " + left + ", " + right + "\n");
            } else if (this.ops.get(i).getCode().equals(TokenCode.DIV)) {
                String right = unaryExps.get(i).toInstructions(cnt, iType, strings);
                res = "%_" + cnt.use();
                // %3 = sdiv i32 %2, 99
                strings.add("   " + res + " = sdiv " + iType + " " + left + ", " + right + "\n");
            } else {
                String right = unaryExps.get(i).toInstructions(cnt, iType, strings);
                res = "%_" + cnt.use();
                // %4 = srem i32 %3, 17
                strings.add("   " + res + " = srem " + iType + " " + left + ", " + right + "\n");
            }
            Counter.put(res, iType);
        }
        return res;
    }
}
