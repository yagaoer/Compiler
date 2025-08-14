package frontend.symbol.stmt.stmtFactor.stmtFor;

import frontend.symbol.SymbolTable;
import frontend.lexer.Token;
import frontend.symbol.stmt.Cond;
import frontend.symbol.stmt.Stmt;
import frontend.symbol.stmt.stmtFactor.StmtFactor;
import llvm.llvmBlock.ForBlock;
import llvm.llvmBlock.LLVMBlock;

public class StmtFor implements StmtFactor {
    /*
    'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
     */
    private Token forTk; // 'for'
    private Token lParent; // '('
    private ForStmt forStmt1; //  [ForStmt]
    private Token semicn1; // ';'
    private Cond cond; // [Cond]
    private Token semicn2; // ';'
    private ForStmt forStmt2; //  [ForStmt]
    private Token rParent; // ')'
    private Stmt stmt; // Stmt
    private SymbolTable symbolTable;

    public StmtFor(Token forTk, Token lParent, ForStmt forStmt1, Token semicn1,
                   Cond cond, Token semicn2, ForStmt forStmt2, Token rParent, Stmt stmt, SymbolTable symbolTable) {
        this.forTk = forTk;
        this.lParent = lParent;
        this.forStmt1 = forStmt1;
        this.semicn1 = semicn1;
        this.cond = cond;
        this.semicn2 = semicn2;
        this.forStmt2 = forStmt2;
        this.rParent = rParent;
        this.stmt = stmt;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.forTk.toString()).append(this.lParent.toString());
        if (this.forStmt1 != null) {
            sb.append(this.forStmt1.toString());
        }
        sb.append(this.semicn1.toString());
        if (this.cond != null) {
            sb.append(this.cond.toString());
        }
        sb.append(this.semicn2.toString());
        if (this.forStmt2 != null) {
            sb.append(this.forStmt2.toString());
        }
        sb.append(this.rParent.toString()).append(this.stmt.toString());
        return sb.toString();
    }

    @Override
    public void fError() {

    }

    public ForBlock parseLLVMBlock() {
        LLVMBlock stmtBlock = stmt.parseLLVMBlock();
        return new ForBlock(forStmt1, cond, forStmt2, stmtBlock, symbolTable);
    }
}
