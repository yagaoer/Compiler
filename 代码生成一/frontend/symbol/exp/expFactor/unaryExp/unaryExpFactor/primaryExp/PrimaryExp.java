package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp;

import frontend.symbol.SymbolCode;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.PrimaryExpFactor;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.UnaryExpFactor;
import llvm.Counter;

import java.util.ArrayList;

public class PrimaryExp implements UnaryExpFactor {
    private final String name = "<PrimaryExp>";
    private PrimaryExpFactor primaryExpFactor;

    public PrimaryExp(PrimaryExpFactor primaryExpFactor) {
        this.primaryExpFactor = primaryExpFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.primaryExpFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public SymbolCode getSymbolCode() {
        return this.primaryExpFactor.getSymbolCode();
    }


    @Override
    public int toRes() {
        return this.primaryExpFactor.toRes();
    }

    @Override
    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        return this.primaryExpFactor.toInstructions(cnt, iType, strings);
    }
}
