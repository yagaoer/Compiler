package frontend.symbol.decl;

import frontend.symbol.block.BlockItemFactor;
import llvm.LLVMGlobal;

import java.util.ArrayList;

public class Decl implements BlockItemFactor {
    private final String name = "<Decl>";
    private DeclFactor declFactor;

    public Decl(DeclFactor declFactor) {
        this.declFactor = declFactor;
    }

    public DeclFactor getDeclFactor() {
        return declFactor;
    }

    public ArrayList<LLVMGlobal> parseLLVMGlobal() {
        return this.declFactor.parseLLVMGlobal();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.declFactor.toString());
        //sb.append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public void fError() {}

    @Override
    public void gError() {

    }
}
