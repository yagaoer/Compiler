package llvm.llvmBlock;

import frontend.symbol.SymbolTable;
import frontend.symbol.stmt.Cond;
import frontend.symbol.stmt.stmtFactor.stmtFor.ForStmt;
import llvm.Counter;

import java.util.ArrayList;

public class ForBlock implements LLVMBlock {
    private ForStmt forStmt1; //  [ForStmt]
    private Cond cond; // [Cond]
    private ForStmt forStmt2; //  [ForStmt]
    private LLVMBlock stmtBlock; // Stmt
    private String iType = "i32";
    private SymbolTable symbolTable;

    public ForBlock(ForStmt forStmt1, Cond cond, ForStmt forStmt2, LLVMBlock stmtBlock, SymbolTable symbolTable) {
        this.forStmt1 = forStmt1;
        this.cond = cond;
        this.forStmt2 = forStmt2;
        this.stmtBlock = stmtBlock;
        this.symbolTable = symbolTable;
    }


    @Override
    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        if (forStmt1 != null) {
            strings.addAll(forStmt1.parseLLVMInstruction().getString(cnt, breakLabel, continueLabel));
        }
        // i < n
        String cmpLabel = "_" + cnt.use();
        // content
        String forLabel = "_" + cnt.use();
        // i++
        String plusLabel = "_" + cnt.use();
        // }
        String endLabel = "_" + cnt.use();
        strings.add("   br label %" + cmpLabel + "\n");
        strings.add(cmpLabel + ":" + "              ; cmpLabel\n");
        if (cond != null) {
            String condRes = cond.toInstructions(cnt, iType, strings, forLabel, endLabel);
//            String tmp = "%_" + cnt.use();
//            // %9 = icmp ne i32 %8, %7
//            strings.add("   " + tmp + " = icmp ne " + iType + " " + condRes + ", " + 0 + "\n");
//            // br i1 %3, label %4, label %7
//            strings.add("   br i1 " + tmp + ", label %" + forLabel + ", label %" + endLabel + "\n");
        } else {
            strings.add("   br label %" + forLabel + "\n");
        }
        strings.add(forLabel + ":" + "              ; forLabel\n");
        strings.addAll(stmtBlock.getString(cnt, endLabel, plusLabel));
        strings.add("   br label %" + plusLabel + "\n");
        strings.add(plusLabel + ":" + "             ; plusLabel(continueLabel)\n");
        if (forStmt2 != null) {
            strings.addAll(forStmt2.parseLLVMInstruction().getString(cnt, breakLabel, continueLabel));
        }
        // br label %1
        strings.add("   br label %" + cmpLabel + "\n");
        strings.add(endLabel + ":" + "              ; endLabel(breakLabel)\n");
        return strings;
    }
}
