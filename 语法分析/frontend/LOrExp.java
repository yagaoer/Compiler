package frontend;

import java.util.ArrayList;

public class LOrExp {
    /*
     LAndExp | LOrExp '||' LAndExp =  LAndExp { '||' LAndExp }
     */
    private final String name = "<LOrExp>";
    private LAndExp lAndExp; // LAndExp
    private ArrayList<Token> ops;
    private ArrayList<LAndExp> lAndExps; // { '||' LAndExp }

    public LOrExp(LAndExp lAndExp, ArrayList<Token> ops, ArrayList<LAndExp> lAndExps) {
        this.lAndExp = lAndExp;
        this.ops = ops;
        this.lAndExps = lAndExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lAndExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.lAndExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }
}
