package frontend;

public class FuncDef {
    private final String name = "<FuncDef>";
    private FuncType funcType; // FuncType
    private Ident ident; //  Ident
    private Token lParent; // '('
    private FuncFParams funcFParams; // [FuncFParams]
    private Token rParent; // ')'
    private Block block; //  Block

    public FuncDef(FuncType funcType, Ident ident, Token lParent,
                   FuncFParams funcFParams, Token rParent, Block block) {
        this.funcType = funcType;
        this.ident = ident;
        this.lParent = lParent;
        this.funcFParams = funcFParams;
        this.rParent = rParent;
        this.block = block;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.funcType.toString()).append(this.ident.toString()).append(this.lParent.toString());
        if (this.funcFParams != null) {
            sb.append(this.funcFParams.toString());
        }
        sb.append(this.rParent.toString()).append(this.block.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
