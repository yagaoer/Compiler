package frontend.symbol.func;

import frontend.symbol.SymbolTable;
import frontend.lexer.Token;
import frontend.symbol.block.Block;
import llvm.llvmBlock.BlockBlock;
import llvm.LLVMFunc;

public class MainFuncDef {
    private final String name = "<MainFuncDef>";
    private Token intTk; // 'int'
    private Token mainTk; // 'main'
    private Token lParent; // '('
    private Token rParent; // ')'
    private Block block; // Block
    private SymbolTable symbolTable;

    public MainFuncDef(Token intTk, Token mainTk, Token lParent, Token rParent, Block block, SymbolTable symbolTable) {
        this.intTk = intTk;
        this.mainTk = mainTk;
        this.lParent = lParent;
        this.rParent = rParent;
        this.block = block;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.intTk.toString()).append(this.mainTk.toString()).append(this.lParent.toString())
                .append(this.rParent.toString()).append(this.block.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }

    public LLVMFunc parseLLVMFunc() {
//        SymbolCode symbolCode = SymbolCode.IntFunc;
//        ArrayList<Symbol> symbols = new ArrayList<>();
//        Symbol symbol = new Symbol(mainTk.getLine(), ident.getToken().getName(), symbolCode, symbols);
//        this.funcFParams.parseLLVMParams(symbolTable, symbols);
//        symbolTable.addSymbol(symbol);

        BlockBlock blockBlock = this.block.parseLLVMBlock();
        return new LLVMFunc(symbolTable, mainTk.getName(), blockBlock);
    }
}
