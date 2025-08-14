package frontend.symbol.decl.varDecl.varDef;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.BType;
import frontend.symbol.Ident;
import frontend.lexer.Token;
import frontend.symbol.exp.ConstExp;
import llvm.llvmInstruction.DeclInstruction;
import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

public class VarDefWithNull implements VarDefFactor {
    private Ident ident; //  Ident
    private Token lBrack;
    private ConstExp constExp;
    private Token rBrack; //  [ '[' ConstExp ']' ]
    private SymbolTable symbolTable;

    public VarDefWithNull(Ident ident, Token lBrack, ConstExp constExp, Token rBrack, SymbolTable symbolTable) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.constExp = constExp;
        this.rBrack = rBrack;
        this.symbolTable = symbolTable;
    }

    @Override
    public LLVMGlobal parseLLVMGlobal(BType bType) {
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        return new LLVMGlobal(symbolTable, symbol, constExp);
    }

    @Override
    public LLVMInstruction LLVMInstruction(BType bType) {
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        return new DeclInstruction(symbolTable, symbol, constExp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.constExp.toString()).append(this.rBrack.toString());
        }
        return sb.toString();
    }
}
