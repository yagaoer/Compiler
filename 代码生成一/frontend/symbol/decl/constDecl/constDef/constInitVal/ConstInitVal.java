package frontend.symbol.decl.constDecl.constDef.constInitVal;

public class ConstInitVal {
    private final String name = "<ConstInitVal>";
    private ConstInitValFactor constInitValFactor;

    public ConstInitVal(ConstInitValFactor constInitValFactor) {
        this.constInitValFactor = constInitValFactor;
    }

    public ConstInitValFactor getConstInitValFactor() {
        return constInitValFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.constInitValFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
