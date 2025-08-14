package frontend.symbol.func.funcParam;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import llvm.Counter;

import java.util.ArrayList;
import java.util.HashMap;

public class FuncRParams {
    /*
     Exp { ',' Exp }
     */
    private final String name = "<FuncRParams>";
    private Exp exp; // Exp
    private ArrayList<Token> commas;
    private ArrayList<Exp> exps;// { ',' Exp }
    private SymbolTable symbolTable;

    public FuncRParams(Exp exp, ArrayList<Token> commas, ArrayList<Exp> exps, SymbolTable symbolTable) {
        this.exp = exp;
        this.commas = commas;
        this.exps = exps;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(exp.toString());
        if (commas != null && exps != null && commas.size() == exps.size()) {
            for (int i = 0; i < commas.size(); i++) {
                sb.append(commas.get(i).toString()).append(exps.get(i).toString());
            }
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }

    public int size() {
        if (exps == null) {
            return 1;
        }
        return exps.size() + 1;
    }

    public ArrayList<Exp> getExps() {
        ArrayList<Exp> arrayList = new ArrayList<>();
        arrayList.addAll(this.exps);
        arrayList.add(0, this.exp);
        return arrayList;
    }

    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings, HashMap<Symbol, String> params, ArrayList<Symbol> funcFParams) {
        // i32 %4, i32 %5
        StringBuilder sb = new StringBuilder();
        String rParam = this.exp.toInstructions(cnt, iType, strings);
        String rParamType = "i32";
        if (rParam.contains("%")) {
            rParamType = Counter.getType(rParam);
        }
        String fParam = params.get(funcFParams.get(0));
        String fParamType = Counter.getType(fParam);
        String tmp;
        if (rParamType.equals("i32") && fParamType.equals("i8")) {
            tmp = "%_" + cnt.use();
            // %10 = trunc i32 %9 to i8
            strings.add("   " + tmp + " = trunc i32 " + rParam + " to i8\n");
            Counter.put(tmp, "i8");
        } else {
            tmp = rParam;
        }
        sb.append(Counter.getType(tmp) + " " + tmp);
        if (commas != null && exps != null && commas.size() == exps.size()) {
            for (int i = 0; i < commas.size(); i++) {
                rParam = exps.get(i).toInstructions(cnt, iType, strings);
                rParamType = Counter.getType(rParam);
                fParam = params.get(funcFParams.get(i + 1));
                fParamType = Counter.getType(fParam);
                if (!rParamType.equals(fParamType)) {
                    tmp = "%_" + cnt.use();
                    // %10 = trunc i32 %9 to i8
                    strings.add("   " + tmp + " = trunc i32 " + rParam + " to i8\n");
                    Counter.put(tmp, "i8");
                } else {
                    tmp = rParam;
                }
                sb.append(", " + Counter.getType(tmp) + " " + tmp);
            }
        }
        return sb.toString();
    }
}
