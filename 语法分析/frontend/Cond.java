package frontend;

public class Cond {
    /*
    LOrExp
     */
    private final String name = "<Cond>";
    private LOrExp lorExp;

    public Cond(LOrExp lorExp) {
        this.lorExp = lorExp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lorExp.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
