package frontend;

public class Character implements PrimaryExpFactor {
    private final String name = "<Character>";
    private Token charConst;

    public Character(Token charConst) {
        this.charConst = charConst;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.charConst.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
