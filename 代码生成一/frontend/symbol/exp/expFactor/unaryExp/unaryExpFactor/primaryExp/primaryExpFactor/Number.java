package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor;

import frontend.symbol.SymbolCode;
import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class Number implements PrimaryExpFactor {
    private final String name = "<Number>";
    private Token intConst;

    public Number(Token intConst) {
        this.intConst = intConst;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append(this.intConst.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public SymbolCode getSymbolCode() {
        return SymbolCode.ConstInt;
    }

    @Override
    public int toRes() {
        return Integer.parseInt(this.intConst.getName());
    }

    @Override
    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        int res = Integer.parseInt(this.intConst.getName());
        Counter.put(res + "", "i32");
        return res + "";
    }
}
