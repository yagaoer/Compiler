package frontend;


import java.util.ArrayList;

public class CompUnitParser {
    private ArrayList<Token> tokens;
    public CompUnitParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public CompUnit parseCompUnit() {
        ArrayList<Decl> decls = parseDecls();
        ArrayList<FuncDef> funcDefs = parseFuncDefs();
        MainFuncDef mainFuncDef = parseMainFuncDef();
        return new CompUnit(decls, funcDefs, mainFuncDef);
    }

    private ArrayList<Decl> parseDecls() {
        ArrayList<Decl> decls = new ArrayList<>();
        while (!tokens.isEmpty()) {
            // 第三个单词是小括号，说明不是Decl
            if (tokens.size() >= 3 && tokens.get(2).getCode().equals(Code.LPARENT)) {
                break;
            }
            decls.add(new DeclParser(tokens).parseDecl());
        }
        return decls;
    }

    private ArrayList<FuncDef> parseFuncDefs() {
        ArrayList<FuncDef> funcDefs = new ArrayList<>();
        while (!tokens.isEmpty()) {
            if (tokens.size() >= 2 && tokens.get(1).getCode().equals(Code.MAINTK)) {
                break;
            }
            funcDefs.add(new FuncDefParser(tokens).parseFuncDef());
        }
        return funcDefs;
    }

    private MainFuncDef parseMainFuncDef() {
        return new MainFuncDefParser(tokens).parseMainFuncDef();
    }
}
