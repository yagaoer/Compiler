package frontend.symbol;

import frontend.symbol.SymbolTable;
import frontend.symbol.decl.Decl;
import frontend.symbol.func.FuncDef;
import frontend.symbol.func.MainFuncDef;

import java.util.ArrayList;

public class CompUnit {
    private final String name = "<CompUnit>";
    private ArrayList<Decl> decls;
    private ArrayList<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;
    private SymbolTable symbolTable;

    public CompUnit(ArrayList<Decl> decls, ArrayList<FuncDef> funcDefs, MainFuncDef mainFuncDef, SymbolTable symbolTable) {
        this.decls = decls;
        this.funcDefs = funcDefs;
        this.mainFuncDef = mainFuncDef;
        this.symbolTable = symbolTable;
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

    public ArrayList<Decl> getDecls() {
        return decls;
    }

    public ArrayList<FuncDef> getFuncDefs() {
        return funcDefs;
    }

    public MainFuncDef getMainFuncDef() {
        return mainFuncDef;
    }
}
