package frontend.symbol.stmt.stmtFactor.stmtIf;

import frontend.symbol.SymbolTable;
import frontend.lexer.Token;
import frontend.symbol.stmt.Cond;
import frontend.symbol.stmt.Stmt;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.IfBlock;
import llvm.llvmBlock.LLVMBlock;

public class StmtIf implements StmtFactor {
    /*
     'if' '(' Cond ')' Stmt [ 'else' Stmt ]
     */
    private Token ifTk; // 'if'
    private Token lParent; // '('
    private Cond cond; // Cond
    private Token rParent; // ')'
    private Stmt stmt1; // Stmt
    private Token elseTk; //
    private Stmt stmt2; // [ 'else' Stmt ]
    private SymbolTable symbolTable;

    public StmtIf(Token ifTk, Token lParent, Cond cond, Token rParent,
                    Stmt stmt1, Token elseTk, Stmt stmt2, SymbolTable symbolTable) {
        this.ifTk = ifTk;
        this.stmt1 = stmt1;
        this.lParent = lParent;
        this.cond = cond;
        this.rParent = rParent;
        this.elseTk = elseTk;
        this.stmt2 = stmt2;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ifTk.toString()).append(this.lParent.toString())
                .append(this.cond.toString()).append(this.rParent.toString()).
                append(this.stmt1.toString());
        if (elseTk != null) {
            sb.append(this.elseTk.toString()).append(this.stmt2.toString());
        }
        return sb.toString();
    }

    @Override
    public void fError() {
        this.stmt1.fError();
        if (this.elseTk != null) {
            this.stmt2.fError();
        }
    }

    public IfBlock parseLLVMBlock() {
        LLVMBlock ifBlock = stmt1.parseLLVMBlock();
        LLVMBlock elseBlock = null;
        if (stmt2 != null) {
            elseBlock = stmt2.parseLLVMBlock();
        }
        return new IfBlock(cond, ifBlock, elseBlock, symbolTable);
    }

}
