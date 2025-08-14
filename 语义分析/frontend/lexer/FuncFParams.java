package frontend.lexer;

import java.util.ArrayList;

public class FuncFParams {
    private final String name = "<FuncFParams>";
    private FuncFParam funcFParam; // FuncFParam
    private ArrayList<Token> commas;
    private ArrayList<FuncFParam> funcFParams; //  { ',' FuncFParam }

    public FuncFParams(FuncFParam funcFParam, ArrayList<Token> commas, ArrayList<FuncFParam> funcFParams) {
        this.funcFParam = funcFParam;
        this.commas = commas;
        this.funcFParams = funcFParams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.funcFParam.toString());
        if (this.commas != null && this.funcFParams != null &&
                this.commas.size() == this.funcFParams.size()) {
            for (int i = 0; i < this.commas.size(); i++) {
                sb.append(this.commas.get(i).toString()).append(this.funcFParams.get(i).toString());
            }
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
