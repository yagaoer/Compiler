package frontend;

public class PrimaryExp implements UnaryExpFactor {
    private final String name = "<PrimaryExp>";
    private PrimaryExpFactor primaryExpFactor;

    public PrimaryExp(PrimaryExpFactor primaryExpFactor) {
        this.primaryExpFactor = primaryExpFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.primaryExpFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
