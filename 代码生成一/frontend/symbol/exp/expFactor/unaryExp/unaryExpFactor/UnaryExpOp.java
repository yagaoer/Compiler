package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor;

import frontend.symbol.SymbolCode;
import frontend.lexer.TokenCode;
import frontend.symbol.exp.expFactor.unaryExp.UnaryExp;
import llvm.Counter;

import java.util.ArrayList;

public class UnaryExpOp implements UnaryExpFactor {
    /*
     UnaryOp UnaryExp
     */
    private UnaryOp unaryOp; // UnaryOp
    private UnaryExp unaryExp;// UnaryExp

    public UnaryExpOp(UnaryOp unaryOp, UnaryExp unaryExp) {
        this.unaryOp = unaryOp;
        this.unaryExp = unaryExp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unaryOp.toString()).append(this.unaryExp.toString());
        return sb.toString();
    }

    @Override
    public SymbolCode getSymbolCode() {
        return this.unaryExp.getSymbolCode();
    }

    @Override
    public int toRes() {
        if (this.unaryOp.getToken().getCode().equals(TokenCode.PLUS)) {
            return this.unaryExp.toRes();
        } else if (this.unaryOp.getToken().getCode().equals(TokenCode.MINU)) {
            return (-1) * this.unaryExp.toRes();
        } else {
            return this.unaryExp.toRes() == 0 ? 1 : 0;
        }
    }

    @Override
    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        iType = "i32";
        if (this.unaryOp.getToken().getCode().equals(TokenCode.PLUS)) {
            return this.unaryExp.toInstructions(cnt, iType, strings);
        } else if (this.unaryOp.getToken().getCode().equals(TokenCode.MINU)){
            String op = "%_" + cnt.use();
            // %3 = sub i32 %2, %1
            strings.add("   " + op + " = sub " + iType + " " + 0 + ", " + 1 + "\n");
            String exp = this.unaryExp.toInstructions(cnt, iType, strings);
            String res = "%_" + cnt.use();
            // %3 = mul i32 %1, %2
            strings.add("   " + res + " = mul " + iType + " " + op + ", " + exp + "\n");
            Counter.put(res, iType);
            return res;
        } else {
            String exp = this.unaryExp.toInstructions(cnt, iType, strings);
            String tmp = "%_" + cnt.use();
            String res = "%_" + cnt.use();
            // %24 = icmp eq i32 %23, 0
            strings.add("   " + tmp + " = icmp eq " + iType + " " + exp + ", " + 0 + "\n");
            // %8 = zext i8 %7 to i32
            strings.add("   " + res + " = zext i1 " + tmp + " to " + iType + "\n");
            Counter.put(res, iType);
            return res;
        }
    }
}
