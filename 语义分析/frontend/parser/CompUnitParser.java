package frontend.parser;


import frontend.*;
import frontend.lexer.*;

import java.util.ArrayList;

public class CompUnitParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;
    public CompUnitParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.symbolTable = new SymbolTable(null);
        this.symbolTable.setField(1);
    }

    public CompUnit parseCompUnit() {
        ArrayList<Decl> decls = parseDecls(symbolTable);
        ArrayList<FuncDef> funcDefs = parseFuncDefs(symbolTable);
        MainFuncDef mainFuncDef = parseMainFuncDef(symbolTable);
        return new CompUnit(decls, funcDefs, mainFuncDef);
    }

    private ArrayList<Decl> parseDecls(SymbolTable symbolTable) {
        ArrayList<Decl> decls = new ArrayList<>();
        while (!tokens.isEmpty()) {
            // 第三个单词是小括号，说明不是Decl
            if (tokens.size() >= 3 && tokens.get(2).getCode().equals(TokenCode.LPARENT)) {
                break;
            }
            decls.add(new DeclParser(tokens, symbolTable).parseDecl());
        }
        return decls;
    }

    private ArrayList<FuncDef> parseFuncDefs(SymbolTable symbolTable) {
        ArrayList<FuncDef> funcDefs = new ArrayList<>();
        while (!tokens.isEmpty()) {
            if (tokens.size() >= 2 && tokens.get(1).getCode().equals(TokenCode.MAINTK)) {
                break;
            }
            funcDefs.add(new FuncDefParser(tokens, symbolTable).parseFuncDef());
        }
        return funcDefs;
    }

    private MainFuncDef parseMainFuncDef(SymbolTable symbolTable) {
        return new MainFuncDefParser(tokens, symbolTable).parseMainFuncDef();
    }
}
