package frontend.lexer;

import java.util.ArrayList;

public class VarInitValArray implements VarInitValFactor {
    private Token lBrace; //  '{'
    private Exp exp; // [ Exp { ',' Exp } ]
    private ArrayList<Token> commas;
    private ArrayList<Exp> exps;
    private Token rBrace; // '}'

    public VarInitValArray(Token lBrace, Exp exp, ArrayList<Token> commas, ArrayList<Exp> exps, Token rBrace) {
        this.lBrace = lBrace;
        this.exp = exp;
        this.commas = commas;
        this.exps = exps;
        this.rBrace = rBrace;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lBrace.toString());
        if (this.exp != null) {
            sb.append(this.exp.toString());
            if (this.commas != null && this.exps != null &&
                    this.commas.size() == this.exps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    sb.append(this.commas.get(i).toString()).append(this.exps.get(i).toString());
                }
            }
        }
        sb.append(this.rBrace.toString());
        return sb.toString();
    }
}
