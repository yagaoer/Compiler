package frontend.symbol.func;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.Ident;
import frontend.lexer.Token;
import frontend.symbol.block.Block;
import frontend.symbol.func.funcParam.FuncFParams;
import frontend.symbol.func.funcType.FuncType;
import llvm.llvmBlock.BlockBlock;
import llvm.LLVMFunc;

public class FuncDef {
    private final String name = "<FuncDef>";
    private FuncType funcType; // FuncType
    private Ident ident; //  Ident
    private Token lParent; // '('
    private FuncFParams funcFParams; // [FuncFParams]
    private Token rParent; // ')'
    private Block block; //  Block
    private SymbolTable symbolTable;

    public FuncDef(FuncType funcType, Ident ident, Token lParent,
                   FuncFParams funcFParams, Token rParent, Block block, SymbolTable symbolTable) {
        this.funcType = funcType;
        this.ident = ident;
        this.lParent = lParent;
        this.funcFParams = funcFParams;
        this.rParent = rParent;
        this.block = block;
        this.symbolTable = symbolTable;
    }

    public LLVMFunc parseLLVMFunc() {
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        BlockBlock blockBlock = this.block.parseLLVMBlock();
        return new LLVMFunc(symbolTable, symbol, funcFParams, blockBlock);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.funcType.toString()).append(this.ident.toString()).append(this.lParent.toString());
        if (this.funcFParams != null) {
            sb.append(this.funcFParams.toString());
        }
        sb.append(this.rParent.toString()).append(this.block.toString()).append(this.name).append("\n");
        return sb.toString();
    }
}
