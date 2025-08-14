package frontend.symbol.exp;

import frontend.symbol.SymbolCode;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitValFactor;
import frontend.symbol.exp.expFactor.AddExp;
import llvm.Counter;

import java.util.ArrayList;

public class Exp implements VarInitValFactor {
    /*
     AddExp
     */
    private final String name = "<Exp>";
    private AddExp addExp;

    public Exp(AddExp addExp) {
        this.addExp = addExp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addExp.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return this.addExp.getSymbolCode();
    }

    public boolean symbolCodeEquals(SymbolCode symbolCode) {
        SymbolCode expCode = getSymbolCode();
        if (symbolCode.equals(SymbolCode.Int) || symbolCode.equals(SymbolCode.Char)) {
            if (expCode.equals(SymbolCode.IntArray) || expCode.equals(SymbolCode.ConstIntArray) ||
                    expCode.equals(SymbolCode.CharArray) || expCode.equals(SymbolCode.ConstCharArray)) {
                return false;
            }
        }
        if (symbolCode.equals(SymbolCode.IntArray) || symbolCode.equals(SymbolCode.CharArray)) {
            if (expCode.equals(SymbolCode.Int) || expCode.equals(SymbolCode.ConstInt) ||
                    expCode.equals(SymbolCode.Char) || expCode.equals(SymbolCode.ConstChar)) {
                return false;
            }
        }
        if (symbolCode.equals(SymbolCode.IntArray)) {
            if (expCode.equals(SymbolCode.CharArray) || expCode.equals(SymbolCode.ConstCharArray)) {
                return false;
            }
        }
        if (symbolCode.equals(SymbolCode.CharArray)) {
            if (expCode.equals(SymbolCode.IntArray) || expCode.equals(SymbolCode.ConstIntArray)) {
                return false;
            }
        }
        return true;
    }

    public int toRes() {
        return this.addExp.toRes();
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        return this.addExp.toInstructions(cnt, iType, strings);
    }
}
