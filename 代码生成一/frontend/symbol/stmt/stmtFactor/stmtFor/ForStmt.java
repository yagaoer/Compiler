package frontend.symbol.stmt.stmtFactor.stmtFor;

import frontend.symbol.SymbolTable;
import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor.LVal;
import llvm.llvmInstruction.LValExpInstruction;

public class ForStmt {
    /*
     LVal '=' Exp
     */
    private final String name = "<ForStmt>";
    private LVal lval; //  LVal
    private Token assign; // '='
    private Exp exp; // Exp
    private SymbolTable symbolTable;

    public ForStmt(LVal lval, Token assign, Exp exp, SymbolTable symbolTable) {
        this.lval = lval;
        this.assign = assign;
        this.exp = exp;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lval.toString()).append(this.assign.toString()).append(this.exp.toString())
                .append(this.name).append("\n");
        return sb.toString();
    }

    public LValExpInstruction parseLLVMInstruction() {
        return new LValExpInstruction(lval, exp);
    }
}
