package frontend;

import java.util.ArrayList;

public class ConstInitValArray implements ConstInitValFactor {
    private Token lBrace; // '{'
    private ConstExp constExp;
    private ArrayList<Token> commas;
    private ArrayList<ConstExp> constExps; // [ ConstExp { ',' ConstExp } ]
    private Token rBrace; // '}'

    public ConstInitValArray(Token lBrace, ConstExp constExp, ArrayList<Token> commas,
                             ArrayList<ConstExp> constExps, Token rBrace) {
        this.lBrace = lBrace;
        this.constExp = constExp;
        this.commas = commas;
        this.constExps = constExps;
        this.rBrace = rBrace;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lBrace.toString());
        if (this.constExp != null) {
            sb.append(this.constExp.toString());
            if (this.commas != null && this.constExps != null
                    && this.commas.size() == this.constExps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    sb.append(this.commas.get(i).toString()).append(this.constExps.get(i).toString());
                }
            }
        }
        sb.append(this.rBrace.toString());
        return sb.toString();
    }
}
