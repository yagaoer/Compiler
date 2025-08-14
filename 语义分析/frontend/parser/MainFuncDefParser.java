package frontend.parser;

import frontend.Symbol;
import frontend.SymbolCode;
import frontend.SymbolTable;
import frontend.TokenCode;
import frontend.lexer.*;

import java.util.ArrayList;

public class MainFuncDefParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public MainFuncDefParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public MainFuncDef parseMainFuncDef() {

        Token intTk = tokens.remove(0);

        Token mainTk = tokens.remove(0);
        addFuncSymbol(mainTk, new ArrayList<>());

        Token lParent = tokens.remove(0);

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        Block block = new BlockParser(tokens, symbolTable).parseBlock();
        gError(block);

        return new MainFuncDef(intTk, mainTk, lParent, rParent, block);
    }

    private void addFuncSymbol(Token mainTk, ArrayList<Symbol> symbols) {
        Symbol symbol = new Symbol(mainTk.getLine(), mainTk.getName(), SymbolCode.IntFunc, symbols);
        // b
        if (this.symbolTable.bError(mainTk.getName())) {
            new Token("b", mainTk.getLine());
        } else {
            this.symbolTable.addSymbol(symbol);
        }

        this.symbolTable = new SymbolTable(this.symbolTable);
        this.symbolTable.setField(this.symbolTable.getField() + 1);
    }

    private void gError(Block block) {
        // g
        block.gError();
    }
}
