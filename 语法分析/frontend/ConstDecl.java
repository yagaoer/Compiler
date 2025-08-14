package frontend;

import java.util.ArrayList;

public class ConstDecl implements DeclFactor {
    private final String name = "<ConstDecl>";
    private Token constTk; // 'const'
    private BType btype; // BType
    private ConstDef constDef; // ConstDef
    private ArrayList<Token> commas;
    private ArrayList<ConstDef> constDefs; // { ',' ConstDef }
    private Token semicn; // ';'

    public ConstDecl(Token constTk, BType btype, ConstDef constDef, ArrayList<Token> commas,
                     ArrayList<ConstDef> constDefs, Token semicn) {
        this.constTk = constTk;
        this.btype = btype;
        this.constDef = constDef;
        this.commas = commas;
        this.constDefs = constDefs;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.constTk.toString()).append(this.btype.toString()).append(this.constDef.toString());
        for (int i = 0; i < commas.size(); i++) {
            sb.append(this.commas.get(i).toString()).append(this.constDefs.get(i).toString());
        }
        sb.append(this.semicn.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
