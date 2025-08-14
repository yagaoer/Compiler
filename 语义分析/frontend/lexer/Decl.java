package frontend.lexer;

public class Decl implements BlockItemFactor {
    private final String name = "<Decl>";
    private DeclFactor declFactor;

    public Decl(DeclFactor declFactor) {
        this.declFactor = declFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.declFactor.toString());
        //sb.append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public void fError() {}

    @Override
    public void gError() {

    }
}
