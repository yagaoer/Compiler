package frontend.lexer;

import java.util.ArrayList;

public class VarDecl implements DeclFactor {
    private final String name = "<VarDecl>";
    private BType btype; //  BType
    private VarDef varDef; //  VarDef
    private ArrayList<Token> commas;
    private ArrayList<VarDef> varDefs; // { ',' VarDef }
    private Token semicn; // ';'

    public VarDecl(BType btype, VarDef varDef, ArrayList<Token> commas, ArrayList<VarDef> varDefs, Token semicn) {
        this.btype = btype;
        this.varDef = varDef;
        this.commas = commas;
        this.varDefs = varDefs;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.btype.toString()).append(this.varDef.toString());
        if (this.commas != null && this.varDefs != null &&
                this.commas.size() == this.varDefs.size()) {
            for (int i = 0; i < this.commas.size(); i++) {
                sb.append(this.commas.get(i).toString()).append(this.varDefs.get(i).toString());
            }
        }
        sb.append(this.semicn.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
