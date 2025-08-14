package llvm.llvmBlock;

import frontend.symbol.SymbolTable;
import frontend.symbol.stmt.Cond;
import llvm.Counter;

import java.util.ArrayList;

public class IfBlock implements LLVMBlock {
    private Cond cond;
    private LLVMBlock ifBlock;
    private LLVMBlock elseBlock;
    private String iType = "i32";
    private SymbolTable symbolTable;

    public IfBlock(Cond cond, LLVMBlock ifBlock, LLVMBlock elseBlock, SymbolTable symbolTable) {
        this.cond = cond;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
        this.symbolTable = symbolTable;
    }

    @Override
    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        String ifLabel = "_" + cnt.use();
        String elseLabel = "_" + cnt.use();
        String endLabel = "_" + cnt.use();
        String condRes = this.cond.toInstructions(cnt, iType, strings, ifLabel, elseLabel);
//        String tmp = "%_" + cnt.use();
//        // %9 = icmp ne i32 %8, %7
//        strings.add("   " + tmp + " = icmp ne " + iType + " " + condRes + ", " + 0 + "\n");
//        // br i1 %3, label %4, label %7
//        strings.add("   br i1 " + tmp + ", label %" + ifLabel + ", label %" + elseLabel + "\n");
        strings.add(ifLabel + ":" + "       ;ifLabel\n");
        strings.addAll(ifBlock.getString(cnt, breakLabel, continueLabel));
        // br label %1
        strings.add("   br label %" + endLabel + "\n");
        strings.add(elseLabel + ":" + "       ;elseLabel\n");
        if (elseBlock != null) {
           strings.addAll(elseBlock.getString(cnt, breakLabel, continueLabel));
        }
        strings.add("   br label %" + endLabel + "\n");
        strings.add(endLabel + ":" + "      ;endLabel\n");
        return strings;

    }
}
