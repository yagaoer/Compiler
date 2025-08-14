package frontend.symbol.decl.constDecl.constDef;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.BType;
import frontend.symbol.Ident;
import frontend.lexer.Token;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitVal;
import frontend.symbol.exp.ConstExp;
import llvm.llvmInstruction.DeclInstruction;
import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

public class ConstDef {
    private final String name = "<ConstDef>";
    private Ident ident; // Ident
    private Token lBrack;
    private ConstExp constExp;
    private Token rBrack; // [ '[' ConstExp ']' ]
    private Token assign; // '='
    private ConstInitVal constInitval; // ConstInitVal
    private SymbolTable symbolTable;

    public ConstDef(Ident ident, Token lBrack, ConstExp constExp,
                    Token rBrack, Token assign, ConstInitVal constInitval, SymbolTable symbolTable) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.constExp = constExp;
        this.rBrack = rBrack;
        this.assign = assign;
        this.constInitval = constInitval;
        this.symbolTable = symbolTable;
    }

    public LLVMGlobal parseLLVMGlobal(BType bType) {
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        return new LLVMGlobal(symbolTable, symbol, constExp, constInitval);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.constExp.toString())
                    .append(this.rBrack.toString());
        }
        sb.append(this.assign.toString()).append(this.constInitval.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }

    public LLVMInstruction parseLLVMInstruction() {
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        return new DeclInstruction(symbolTable, symbol, constExp, constInitval);
    }
}
