package frontend.lexer;

import java.util.ArrayList;

public class CompUnit {
    private final String name = "<CompUnit>";
    private ArrayList<Decl> decls;
    private ArrayList<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;

    public CompUnit(ArrayList<Decl> decls, ArrayList<FuncDef> funcDefs, MainFuncDef mainFuncDef) {
        this.decls = decls;
        this.funcDefs = funcDefs;
        this.mainFuncDef = mainFuncDef;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.decls != null) {
            for (int i = 0; i < this.decls.size(); i++) {
                sb.append(this.decls.get(i).toString());
            }
        }
        if (this.funcDefs != null) {
            for (int i = 0; i < this.funcDefs.size(); i++) {
                sb.append(this.funcDefs.get(i).toString());
            }
        }
        sb.append(this.mainFuncDef.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
