package frontend;

import java.util.ArrayList;

public class ConstDef {
    private final String name = "<ConstDef>";
    private Ident ident; // Ident
    private Token lBrack;
    private ConstExp constExp;
    private Token rBrack; // [ '[' ConstExp ']' ]
    private Token assign; // '='
    private ConstInitVal constInitval; // ConstInitVal

    public ConstDef(Ident ident, Token lBrack, ConstExp constExp,
                    Token rBrack, Token assign, ConstInitVal constInitval) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.constExp = constExp;
        this.rBrack = rBrack;
        this.assign = assign;
        this.constInitval = constInitval;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.constExp.toString())
                    .append(this.rBrack.toString());
        }
        sb.append(this.assign.toString()).append(this.constInitval.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }
}
