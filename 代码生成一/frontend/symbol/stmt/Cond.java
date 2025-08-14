package frontend.symbol.stmt;

import frontend.symbol.SymbolTable;
import frontend.symbol.exp.expFactor.LOrExp;
import llvm.Counter;

import java.util.ArrayList;

public class Cond {
    /*
    LOrExp
     */
    private final String name = "<Cond>";
    private LOrExp lorExp;
    private SymbolTable symbolTable;

    public Cond(LOrExp lorExp, SymbolTable symbolTable) {
        this.lorExp = lorExp;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lorExp.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings, String ifLabel, String elseLabel) {
        return this.lorExp.toInstructions(cnt, iType, strings, ifLabel, elseLabel);
    }
}
