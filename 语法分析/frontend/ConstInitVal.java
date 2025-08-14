package frontend;

public class ConstInitVal {
    private final String name = "<ConstInitVal>";
    private ConstInitValFactor constInitValFactor;

    public ConstInitVal(ConstInitValFactor constInitValFactor) {
        this.constInitValFactor = constInitValFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.constInitValFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
