package llvm;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.func.funcParam.FuncFParams;
import llvm.llvmBlock.BlockBlock;

import java.util.ArrayList;
import java.util.HashMap;

public class LLVMFunc {
    private SymbolTable symbolTable;
    private String name;
    private SymbolCode symbolCode;
    private FuncFParams funcFParams = null; // 函数参数
    private BlockBlock blockBlock;
    private Symbol symbol;
    private String iType = "i32";


    // Func
    public LLVMFunc(SymbolTable symbolTable, Symbol symbol, FuncFParams funcRParams, BlockBlock blockBlock) {
        this.symbolTable = symbolTable;
        this.symbol = symbol;
        this.name = symbol.getName();
        this.symbolCode = symbol.getSymbolCode();
        this.funcFParams = funcRParams;
        this.blockBlock = blockBlock;
    }

    // Main
    public LLVMFunc(SymbolTable symbolTable, String name, BlockBlock blockBlock) {
        this.symbolTable = symbolTable;
        this.symbol = null;
        this.name = name;
        this.blockBlock = blockBlock;
        this.symbolCode = SymbolCode.IntFunc;
    }

//    // FuncFPram
//    public LLVMGlobal(SymbolTable symbolTable, String name, SymbolCode symbolCode, int array) {
//        this.symbolTable = symbolTable;
//        this.name = name;
//        this.symbolCode = symbolCode;
//        this.array = array;
//    }

    public ArrayList<String> getString(Counter cnt) {
        ArrayList<String> strings = new ArrayList<>();
        // define dso_local i32 @foo(i32 %0, i32* %1) {
        HashMap<Symbol, String> params = new HashMap<>();
        String paramString = "";
        if (funcFParams != null) {
            paramString = funcFParams.getString(cnt, params);
        }
        if (symbol != null) {
            symbol.setParams(params);
        }
        String funcType = "i32";
        if (symbolCode.equals(SymbolCode.VoidFunc)) {
            funcType = "void";
        }
        strings.add("define dso_local " + funcType + " @" + name + "(" + paramString + ") {" + "\n");
        for (Symbol symbol : params.keySet()) {
            String fParam = params.get(symbol);
            String alloca = "   %" + symbol.getName() + " = alloca " + Counter.getType(fParam) + "\n";
            String store = "   store " + Counter.getType(fParam) + " " + fParam
                    + ", " + Counter.getType(fParam) + "*" + " %" + symbol.getName() + "\n";
            strings.add(alloca + store);
        }

//        String endLabel = String.valueOf(cnt++);
//        String blockLabel = this.block.toInstructions(cnt, iType, strings, symbolTable);
//        strings.add(endLabel + ":" + "\n");
//        funcFParams.toInstructions(cnt, iType, strings);
        strings.addAll(blockBlock.getString(cnt, "", ""));
        if (symbolCode.equals(SymbolCode.VoidFunc) && !strings.get(strings.size() - 1).contains("ret")) {
            strings.add("   ret void" + "\n");
        }
        strings.add("}" + "\n\n");
        return strings;
    }
}
