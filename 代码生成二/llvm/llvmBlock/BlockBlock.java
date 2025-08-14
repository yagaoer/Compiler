package llvm.llvmBlock;

import llvm.Counter;

import java.util.ArrayList;

public class BlockBlock implements LLVMBlock {
    private ArrayList<LLVMBlock> blocks;

    public BlockBlock(ArrayList<LLVMBlock> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<String> getString(Counter cnt, String breakLabel, String continueLabel) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {
            strings.addAll(blocks.get(i).getString(cnt, breakLabel, continueLabel));
        }
        return strings;
    }
}
