package frontend;

import java.util.ArrayList;

public class MulExp {
    /*
     UnaryExp | MulExp ('*' | '/' | '%') UnaryExp == UnaryExp {('*' | '/' | '%') UnaryExp}
     */
    private final String name = "<MulExp>";
    private UnaryExp unaryExp; // UnaryExp
    private ArrayList<Token> ops;
    private ArrayList<UnaryExp> unaryExps; // { ('*' | '/' | '%') UnaryExp }

    public MulExp(UnaryExp unaryExp, ArrayList<Token> ops, ArrayList<UnaryExp> unaryExps) {
        this.unaryExp = unaryExp;
        this.ops = ops;
        this.unaryExps = unaryExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(unaryExp.toString()).append(this.name).append("\n");
        for (int i = 0; i < ops.size(); i++) {
            sb.append(ops.get(i).toString()).append(unaryExps.get(i).toString())
                    .append(this.name).append("\n");
        }
        return sb.toString();
    }
}
