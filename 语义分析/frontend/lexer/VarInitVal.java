package frontend.lexer;

public class VarInitVal {
    private final String name = "<InitVal>";
    private VarInitValFactor varInitValFactor;

    public VarInitVal(VarInitValFactor varInitValFactor) {
        this.varInitValFactor = varInitValFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.varInitValFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
