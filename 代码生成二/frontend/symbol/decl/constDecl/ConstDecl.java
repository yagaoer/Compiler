package frontend.symbol.decl.constDecl;

import frontend.symbol.SymbolTable;
import frontend.symbol.BType;
import frontend.symbol.decl.DeclFactor;
import frontend.lexer.Token;
import frontend.symbol.decl.constDecl.constDef.ConstDef;
import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class ConstDecl implements DeclFactor {
    private final String name = "<ConstDecl>";
    private Token constTk; // 'const'
    private BType btype; // BType
    private ConstDef constDef; // ConstDef
    private ArrayList<Token> commas;
    private ArrayList<ConstDef> constDefs; // { ',' ConstDef }
    private Token semicn; // ';'
    private SymbolTable symbolTable;

    public ConstDecl(Token constTk, BType btype, ConstDef constDef, ArrayList<Token> commas,
                     ArrayList<ConstDef> constDefs, Token semicn, SymbolTable symbolTable) {
        this.constTk = constTk;
        this.btype = btype;
        this.constDef = constDef;
        this.commas = commas;
        this.constDefs = constDefs;
        this.semicn = semicn;
        this.symbolTable = symbolTable;
    }

    @Override
    public ArrayList<LLVMGlobal> parseLLVMGlobal() {
        ArrayList<LLVMGlobal> globals = new ArrayList<>();
        globals.add(this.constDef.parseLLVMGlobal(this.btype));
        for (int i = 0; i < this.constDefs.size(); i++) {
            globals.add(this.constDefs.get(i).parseLLVMGlobal(this.btype));
        }
        return globals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.constTk.toString()).append(this.btype.toString()).append(this.constDef.toString());
        for (int i = 0; i < commas.size(); i++) {
            sb.append(this.commas.get(i).toString()).append(this.constDefs.get(i).toString());
        }
        sb.append(this.semicn.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public ArrayList<LLVMInstruction> parseLLVMInstructions() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(this.constDef.parseLLVMInstruction());
        for (int i = 0; i < this.constDefs.size(); i++) {
            instructions.add(this.constDefs.get(i).parseLLVMInstruction());
        }
        return instructions;
    }
}
