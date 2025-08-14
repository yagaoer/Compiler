package frontend;

import java.util.ArrayList;

public class RelExp {
    /*
     AddExp | RelExp ('<' | '>' | '<=' | '>=') AddExp = AddExp { ('<' | '>' | '<=' | '>=') AddExp }
     */
    private final String name = "<RelExp>";
    private AddExp addExp; // AddExp
    private ArrayList<Token> ops;
    private ArrayList<AddExp> addExps; // { ('+' | '−') AddExp }

    public RelExp(AddExp addExp, ArrayList<Token> ops, ArrayList<AddExp> addExps) {
        this.addExp = addExp;
        this.ops = ops;
        this.addExps = addExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.addExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }
}
