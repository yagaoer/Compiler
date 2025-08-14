package frontend;

import java.util.ArrayList;

public class LAndExp {
    /*
    EqExp | LAndExp '&&' EqExp = EqExp { '&&' EqExp }
     */
    private final String name = "<LAndExp>";
    private EqExp eqExp; // EqExp
    private ArrayList<Token> ops;
    private ArrayList<EqExp> eqExps; // { '&&' EqExp }

    public LAndExp(EqExp eqExp, ArrayList<Token> ops, ArrayList<EqExp> eqExps) {
        this.eqExp = eqExp;
        this.ops = ops;
        this.eqExps = eqExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.eqExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.eqExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }
}
