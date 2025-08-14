package frontend.symbol.func;

import frontend.lexer.*;
import frontend.symbol.func.funcParam.FuncFParamsParser;
import frontend.symbol.func.funcType.FuncTypeParser;
import frontend.symbol.IdentParser;
import frontend.symbol.Ident;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.block.Block;
import frontend.symbol.block.BlockParser;
import frontend.symbol.func.funcParam.FuncFParams;
import frontend.symbol.func.funcType.*;

import java.util.ArrayList;

public class FuncDefParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public FuncDefParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public FuncDef parseFuncDef() {

        FuncType funcType = new FuncTypeParser(tokens).parseFuncType();

        Ident ident = new IdentParser(tokens).parseIdent();
        ArrayList<Symbol> symbols = new ArrayList<>();
        SymbolTable symbolTableInFunc = addFuncSymbol(ident, funcType.getFuncTypeFactor(), symbols);

        Token lParent = tokens.remove(0);


        FuncFParams funcFParams = null;
        if (tokens.get(0).getCode().equals(TokenCode.INTTK) ||
                tokens.get(0).getCode().equals(TokenCode.CHARTK)) {
            funcFParams = new FuncFParamsParser(tokens, symbolTableInFunc, symbols).parseFuncFParams();
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        Block block = new BlockParser(tokens, symbolTableInFunc).parseBlock();
        returnError(block, funcType.getFuncTypeFactor());

        return new FuncDef(funcType, ident, lParent, funcFParams, rParent, block, symbolTable);
    }

    private SymbolTable addFuncSymbol(Ident ident, FuncTypeFactor funcTypeFactor, ArrayList<Symbol> symbols) {
        SymbolCode symbolCode;
        if (funcTypeFactor instanceof FuncTypeChar) {
            symbolCode = SymbolCode.CharFunc;
        } else if (funcTypeFactor instanceof FuncTypeInt) {
            symbolCode = SymbolCode.IntFunc;
        } else {
            symbolCode = SymbolCode.VoidFunc;
        }
        Symbol symbol = new Symbol(ident.getToken().getLine(), ident.getToken().getName(), symbolCode, symbols);
        // b
        if (this.symbolTable.bError(ident.getToken().getName())) {
            new Token("b", ident.getToken().getLine());
        } else {
            this.symbolTable.addSymbol(symbol);
        }

        SymbolTable symbolTableInFunc = new SymbolTable(this.symbolTable);
        symbolTableInFunc.setField(this.symbolTable.getField() + 1);
        return symbolTableInFunc;
    }

    private void returnError(Block block, FuncTypeFactor funcTypeFactor) {
        if (funcTypeFactor instanceof FuncTypeVoid) {
            // f
            block.fError();
        } else {
            // g
            block.gError();
        }
    }
}
