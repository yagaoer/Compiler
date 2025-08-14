package frontend.symbol.block;

import frontend.symbol.SymbolTable;
import frontend.lexer.*;
import frontend.symbol.decl.Decl;
import frontend.symbol.decl.DeclFactor;
import frontend.symbol.stmt.*;
import frontend.symbol.stmt.stmtFactor.*;
import frontend.symbol.stmt.stmtFactor.stmtBlock.StmtBlock;
import frontend.symbol.stmt.stmtFactor.stmtExp.StmtExp;
import frontend.symbol.stmt.stmtFactor.stmtContinue.StmtContinue;
import frontend.symbol.stmt.stmtFactor.stmtBreak.StmtBreak;
import frontend.symbol.stmt.stmtFactor.stmtFor.StmtFor;
import frontend.symbol.stmt.stmtFactor.stmtIf.StmtIf;
import frontend.symbol.stmt.stmtFactor.stmtLVal.StmtLValAssignExp;
import frontend.symbol.stmt.stmtFactor.stmtLVal.StmtLValAssignGetChar;
import frontend.symbol.stmt.stmtFactor.stmtLVal.StmtLValAssignGetInt;
import frontend.symbol.stmt.stmtFactor.stmtPrintf.StmtPrintf;
import frontend.symbol.stmt.stmtFactor.stmtReturn.StmtReturn;
import llvm.llvmBlock.BlockBlock;
import llvm.llvmBlock.InstructionsBlock;
import llvm.llvmBlock.LLVMBlock;
import llvm.llvmInstruction.LLVMInstruction;

import java.util.ArrayList;

public class Block {
    private final String name = "<Block>";
    private Token lBrace; // '{'
    private ArrayList<BlockItem> blockItems; // { BlockItem }
    private Token rBrace; // '}'
    private SymbolTable symbolTable;

    public Block(Token lBrace, ArrayList<BlockItem> blockItems, Token rBrace, SymbolTable symbolTable) {
        this.lBrace = lBrace;
        this.blockItems = blockItems;
        this.rBrace = rBrace;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lBrace.toString());
        if (this.blockItems != null) {
            for (int i = 0; i < this.blockItems.size(); i++) {
                sb.append(this.blockItems.get(i).toString());
            }
        }
        sb.append(this.rBrace.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    public ArrayList<BlockItem> getBlockItems() {
        return blockItems;
    }

    public void fError() {
        if (blockItems.isEmpty()) {
            return;
        }
        for (BlockItem blockItem : blockItems) {
            blockItem.getBlockItemFactor().fError();
        }
    }

    public void gError() {
        if (blockItems.isEmpty()) {
            new Token("g", rBrace.getLine());
        } else {
            BlockItemFactor blockItemFactor = blockItems.get(blockItems.size() - 1).getBlockItemFactor();
            if (blockItemFactor instanceof Stmt) {
                StmtFactor stmtFactor = ((Stmt) blockItemFactor).getStmtFactor();
                if (stmtFactor instanceof StmtReturn) {
                    return;
                }
            }
            new Token("g", rBrace.getLine());
        }
    }

    public BlockBlock parseLLVMBlock() {
        ArrayList<LLVMBlock> blocks = new ArrayList<>();
        for (int i = 0; i < this.blockItems.size(); i++) {
            BlockItemFactor blockItemFactor = this.blockItems.get(i).getBlockItemFactor();
            if (blockItemFactor instanceof Stmt) {
                StmtFactor stmtFactor = ((Stmt) blockItemFactor).getStmtFactor();
                if (stmtFactor instanceof StmtIf) {
                    blocks.add(((StmtIf) stmtFactor).parseLLVMBlock());
                    continue;
                }
                if (stmtFactor instanceof StmtFor) {
                    blocks.add(((StmtFor) stmtFactor).parseLLVMBlock());
                    continue;
                }
                if (stmtFactor instanceof StmtBlock) {
                    blocks.add(((StmtBlock) stmtFactor).parseLLVMBlock());
                    continue;
                }
            }
            // 解析指令
            ArrayList<LLVMInstruction> instructions = new ArrayList<>();
            for (int j = i; j < this.blockItems.size(); j++) {
                BlockItemFactor blockItemFactorIn = this.blockItems.get(j).getBlockItemFactor();
                if (blockItemFactorIn instanceof Stmt) {
                    StmtFactor stmtFactorIn = ((Stmt) blockItemFactorIn).getStmtFactor();
                    if (stmtFactorIn instanceof StmtIf || stmtFactorIn instanceof StmtFor ||
                            stmtFactorIn instanceof StmtBlock) {
                        break;
                    }
                    if (stmtFactorIn instanceof StmtReturn) {
                        instructions.add(((StmtReturn) stmtFactorIn).parseLLVMInstructions());
                    } else if (stmtFactorIn instanceof StmtPrintf) {
                        instructions.add(((StmtPrintf) stmtFactorIn).parseLLVMInstructions());
                    } else if (stmtFactorIn instanceof StmtLValAssignGetInt) {
                        instructions.add(((StmtLValAssignGetInt) stmtFactorIn).parseLLVMInstruction());
                    } else if (stmtFactorIn instanceof StmtLValAssignGetChar) {
                        instructions.add(((StmtLValAssignGetChar) stmtFactorIn).parseLLVMInstruction());
                    } else if (stmtFactorIn instanceof StmtLValAssignExp) {
                        instructions.add(((StmtLValAssignExp) stmtFactorIn).parseLLVMInstruction());
                    } else if (stmtFactorIn instanceof StmtExp) {
                        instructions.add(((StmtExp) stmtFactorIn).parseLLVMInstruction());
                    } else if (stmtFactorIn instanceof StmtBreak) {
                        instructions.add(((StmtBreak) stmtFactorIn).parseLLVMInstruction());
                    } else if (stmtFactorIn instanceof StmtContinue) {
                        instructions.add(((StmtContinue) stmtFactorIn).parseLLVMInstruction());
                    }
                } else {
                    // decl
                    DeclFactor declFactor = ((Decl) blockItemFactorIn).getDeclFactor();
                    instructions.addAll(declFactor.parseLLVMInstructions());
                }
                i = j;
            }
            blocks.add(new InstructionsBlock(instructions));
        }
        return new BlockBlock(blocks);
    }
}
