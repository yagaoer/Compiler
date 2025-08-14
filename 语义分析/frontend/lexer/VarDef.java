package frontend.lexer;

public class VarDef {
    private final String name = "<VarDef>";
    private VarDefFactor varDefFactor;

    public VarDef(VarDefFactor varDefFactor) {
        this.varDefFactor = varDefFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.varDefFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
