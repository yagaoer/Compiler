package frontend;

import java.util.ArrayList;

public class EqExp {
    /*
     RelExp | EqExp ('==' | '!=') RelExp = RelExp { ('==' | '!=') RelExp }
     */
    private final String name = "<EqExp>";
    private RelExp relExp; // RelExp
    private ArrayList<Token> ops;
    private ArrayList<RelExp> relExps; // { ('==' | '!=') RelExp }

    public EqExp(RelExp relExp, ArrayList<Token> ops, ArrayList<RelExp> relExps) {
        this.relExp = relExp;
        this.ops = ops;
        this.relExps = relExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.relExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.relExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }
}
