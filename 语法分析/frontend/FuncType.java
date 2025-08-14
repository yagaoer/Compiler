package frontend;

public class FuncType {
    private final String name = "<FuncType>";
    private FuncTypeFactor funcTypeFactor;

    public FuncType(FuncTypeFactor funcTypeFactor) {
        this.funcTypeFactor = funcTypeFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.funcTypeFactor.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
