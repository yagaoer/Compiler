package frontend.symbol.exp;

import frontend.symbol.SymbolTable;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitValFactor;
import frontend.symbol.exp.expFactor.AddExp;
import llvm.Counter;

import java.util.ArrayList;

public class ConstExp implements ConstInitValFactor {
    private final String name = "<ConstExp>";
    private AddExp addExp;
    private SymbolTable symbolTable;

    public ConstExp(AddExp addExp, SymbolTable symbolTable) {
        this.addExp = addExp;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addExp.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public int toRes() {
        return this.addExp.toRes();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        return this.addExp.toInstructions(cnt, iType, strings);
    }
}
