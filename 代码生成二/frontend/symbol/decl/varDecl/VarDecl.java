package frontend.symbol.decl.varDecl;

import frontend.symbol.SymbolTable;
import frontend.symbol.BType;
import frontend.lexer.Token;
import frontend.symbol.decl.DeclFactor;
import frontend.symbol.decl.varDecl.varDef.VarDef;
import llvm.LLVMGlobal;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class VarDecl implements DeclFactor {
    private final String name = "<VarDecl>";
    private BType btype; //  BType
    private VarDef varDef; //  VarDef
    private ArrayList<Token> commas;
    private ArrayList<VarDef> varDefs; // { ',' VarDef }
    private Token semicn; // ';'
    private SymbolTable symbolTable;

    public VarDecl(BType btype, VarDef varDef, ArrayList<Token> commas, ArrayList<VarDef> varDefs, Token semicn, SymbolTable symbolTable) {
        this.btype = btype;
        this.varDef = varDef;
        this.commas = commas;
        this.varDefs = varDefs;
        this.semicn = semicn;
        this.symbolTable = symbolTable;
    }

    public ArrayList<LLVMGlobal> parseLLVMGlobal() {
        ArrayList<LLVMGlobal> globals = new ArrayList<>();
        globals.add(this.varDef.parseLLVMGlobal(this.btype));
        for (int i = 0; i < this.varDefs.size(); i++) {
            globals.add(this.varDefs.get(i).parseLLVMGlobal(this.btype));
        }
        return globals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.btype.toString()).append(this.varDef.toString());
        if (this.commas != null && this.varDefs != null &&
                this.commas.size() == this.varDefs.size()) {
            for (int i = 0; i < this.commas.size(); i++) {
                sb.append(this.commas.get(i).toString()).append(this.varDefs.get(i).toString());
            }
        }
        sb.append(this.semicn.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public ArrayList<LLVMInstruction> parseLLVMInstructions() {
        ArrayList<LLVMInstruction> instructions = new ArrayList<>();
        instructions.add(this.varDef.parseLLVMInstruction(this.btype));
        for (int i = 0; i < this.varDefs.size(); i++) {
            instructions.add(this.varDefs.get(i).parseLLVMInstruction(this.btype));
        }
        return instructions;
    }
}
