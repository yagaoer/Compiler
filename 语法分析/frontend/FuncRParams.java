package frontend;

import java.util.ArrayList;

public class FuncRParams {
    /*
     Exp { ',' Exp }
     */
    private final String name = "<FuncRParams>";
    private Exp exp; // Exp
    private ArrayList<Token> commas;
    private ArrayList<Exp> exps;// { ',' Exp }

    public FuncRParams(Exp exp, ArrayList<Token> commas, ArrayList<Exp> exps) {
        this.exp = exp;
        this.commas = commas;
        this.exps = exps;
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
}
