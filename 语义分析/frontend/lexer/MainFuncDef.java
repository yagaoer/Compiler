package frontend.lexer;

public class MainFuncDef {
    private final String name = "<MainFuncDef>";
    private Token intTk; // 'int'
    private Token mainTk; // 'main'
    private Token lParent; // '('
    private Token rParent; // ')'
    private Block block; // Block

    public MainFuncDef(Token intTk, Token mainTk, Token lParent, Token rParent, Block block) {
        this.intTk = intTk;
        this.mainTk = mainTk;
        this.lParent = lParent;
        this.rParent = rParent;
        this.block = block;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.intTk.toString()).append(this.mainTk.toString()).append(this.lParent.toString())
                .append(this.rParent.toString()).append(this.block.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }
}
