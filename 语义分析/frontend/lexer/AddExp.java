package frontend.lexer;

import frontend.SymbolCode;

import java.util.ArrayList;

public class AddExp {
    /*
      MulExp | AddExp ('+' | '−') MulExp ==  MulExp { ('+' | '−') MulExp }
     */
    private final String name = "<AddExp>";
    private MulExp mulExp; // MulExp
    private ArrayList<Token> ops;
    private ArrayList<MulExp> mulExps; // { ('+' | '−') MulExp }

    public AddExp(MulExp mulExp, ArrayList<Token> ops, ArrayList<MulExp> mulExps) {
        this.mulExp = mulExp;
        this.ops = ops;
        this.mulExps = mulExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mulExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < this.ops.size(); i++) {
            sb.append(this.ops.get(i).toString()).append(this.mulExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }

    public SymbolCode getSymbolCode() {
        return this.mulExp.getSymbolCode();
    }
}
