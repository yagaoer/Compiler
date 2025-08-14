package frontend.symbol.decl.varDecl.varDef;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolTable;
import frontend.symbol.BType;
import frontend.symbol.Ident;
import frontend.lexer.Token;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitVal;
import frontend.symbol.exp.ConstExp;
import llvm.llvmInstruction.DeclInstruction;
import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

public class VarDefWithAssign implements VarDefFactor {
    private Ident ident; //  Ident
    private Token lBrack;
    private ConstExp constExp;
    private Token rBrack; // [ '[' ConstExp ']' ]
    private Token assign; // '='
    private VarInitVal varInitVal; // InitVal
    private SymbolTable symbolTable;

    public VarDefWithAssign(Ident ident, Token lBrack, ConstExp constExp, Token rBrack,
                            Token assign, VarInitVal varInitVal, SymbolTable symbolTable) {
        this.ident = ident;
        this.lBrack = lBrack;
        this.constExp = constExp;
        this.rBrack = rBrack;
        this.assign = assign;
        this.varInitVal = varInitVal;
        this.symbolTable = symbolTable;
    }

    @Override
    public LLVMGlobal parseLLVMGlobal(BType bType) {
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        return new LLVMGlobal(symbolTable, symbol, constExp, varInitVal);
    }

    @Override
    public LLVMInstruction LLVMInstruction(BType bType) {
        Symbol symbol = symbolTable.getSymbol(ident.getToken().getName());
        return new DeclInstruction(symbolTable, symbol, constExp, varInitVal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (this.lBrack != null) {
            sb.append(this.lBrack.toString()).append(this.constExp.toString()).append(this.rBrack.toString());
        }
        sb.append(this.assign.toString()).append(this.varInitVal.toString());
        return sb.toString();
    }
}
