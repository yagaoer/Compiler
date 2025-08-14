package frontend;

public class Exp implements VarInitValFactor{
    /*
     AddExp
     */
    private final String name = "<Exp>";
    private AddExp addExp;

    public Exp(AddExp addExp) {
        this.addExp = addExp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addExp.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
