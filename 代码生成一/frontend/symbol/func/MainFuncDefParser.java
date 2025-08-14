package frontend.symbol.func;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.lexer.TokenCode;
import frontend.lexer.*;
import frontend.symbol.block.Block;
import frontend.symbol.block.BlockParser;
import frontend.symbol.func.MainFuncDef;

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
        SymbolTable symbolTableInFunc = addFuncSymbol(mainTk, new ArrayList<>());

        Token lParent = tokens.remove(0);

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        Block block = new BlockParser(tokens, symbolTableInFunc).parseBlock();
        gError(block);

        return new MainFuncDef(intTk, mainTk, lParent, rParent, block, symbolTable);
    }

    private SymbolTable addFuncSymbol(Token mainTk, ArrayList<Symbol> symbols) {
        Symbol symbol = new Symbol(mainTk.getLine(), mainTk.getName(), SymbolCode.IntFunc, symbols);
        // b
        if (this.symbolTable.bError(mainTk.getName())) {
            new Token("b", mainTk.getLine());
        } else {
            this.symbolTable.addSymbol(symbol);
        }

        SymbolTable symbolTableInFunc = new SymbolTable(this.symbolTable);
        symbolTableInFunc.setField(symbolTableInFunc.getField() + 1);
        return symbolTableInFunc;
    }

    private void gError(Block block) {
        // g
        block.gError();
    }
}
