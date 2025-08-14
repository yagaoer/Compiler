package frontend.symbol.exp.expFactor.unaryExp;

import frontend.symbol.SymbolCode;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.UnaryExpFactor;
import llvm.Counter;

import java.util.ArrayList;

public class UnaryExp {
    private final String name = "<UnaryExp>";
    private UnaryExpFactor unaryExpFactor;

    public UnaryExp(UnaryExpFactor unaryExpFactor) {
        this.unaryExpFactor = unaryExpFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unaryExpFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return this.unaryExpFactor.getSymbolCode();
    }

    public int toRes() {
        return this.unaryExpFactor.toRes();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        return this.unaryExpFactor.toInstructions(cnt, iType, strings);
    }
}
