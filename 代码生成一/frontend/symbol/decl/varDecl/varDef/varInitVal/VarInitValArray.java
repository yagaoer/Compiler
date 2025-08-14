package frontend.symbol.decl.varDecl.varDef.varInitVal;

import frontend.lexer.Token;
import frontend.symbol.exp.Exp;
import llvm.Counter;

import java.util.ArrayList;

public class VarInitValArray implements VarInitValFactor {
    private Token lBrace; //  '{'
    private Exp exp; // [ Exp { ',' Exp } ]
    private ArrayList<Token> commas;
    private ArrayList<Exp> exps;
    private Token rBrace; // '}'

    public VarInitValArray(Token lBrace, Exp exp, ArrayList<Token> commas, ArrayList<Exp> exps, Token rBrace) {
        this.lBrace = lBrace;
        this.exp = exp;
        this.commas = commas;
        this.exps = exps;
        this.rBrace = rBrace;
    }

    public String llvmArrayInit(int num, String iType) {
        StringBuilder sb = new StringBuilder();
        if (this.exp != null) {
            sb.append("[").append(iType).append(" ").append(exp.toRes());
            if (this.commas != null && this.exps != null
                    && this.commas.size() == this.exps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    sb.append(", ").append(iType).append(" ").append(this.exps.get(i).toRes());
                }
                for (int i = this.commas.size() + 1; i < num; i++) {
                    sb.append(", ").append(iType).append(" ").append("0");
                }
            } else {
                for (int i = 1; i < num; i++) {
                    sb.append(", ").append(iType).append(" ").append("0");
                }
            }
            sb.append("]");
        } else {
            sb.append("zeroinitializer");
        }
        return sb.toString();
    }

    public int toRes(int index) {
        if (index == 0) {
            return this.exp.toRes();
        } else {
            return this.exps.get(index - 1).toRes();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lBrace.toString());
        if (this.exp != null) {
            sb.append(this.exp.toString());
            if (this.commas != null && this.exps != null &&
                    this.commas.size() == this.exps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    sb.append(this.commas.get(i).toString()).append(this.exps.get(i).toString());
                }
            }
        }
        sb.append(this.rBrace.toString());
        return sb.toString();
    }

    public String localArrayInit(Counter cnt, int num, String name, String ptrType, String iType, ArrayList<String> strings) {
        StringBuilder sb = new StringBuilder();
        if (this.exp != null) {
            String ptr = "%_" + cnt.use();
            // %3 = getelementptr i32, i32* @a, i32 0
            sb.append("   " + ptr + " = getelementptr " + ptrType + ", "
                    + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + 0 + "\n");
            String res = toI8(exp.toInstructions(cnt, iType, strings), iType, cnt, strings);
            // store i32 1, i32* %2
            sb.append("   store " + iType + " " + res + ", "
                    + iType + "* " + ptr + "\n");
            if (this.commas != null && this.exps != null
                    && this.commas.size() == this.exps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    String tmp = "%_" + cnt.use();
                    // %3 = getelementptr i32, i32* @a, i32 0
                    sb.append("   " + tmp + " = getelementptr " + ptrType + ", "
                            + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + (i+1) + "\n");
                    String res0 = toI8(exps.get(i).toInstructions(cnt, iType, strings), iType, cnt, strings);
                    // store i32 1, i32* %2
                    sb.append("   store " + iType + " " + res0 + ", "
                            + iType + "* " + tmp + "\n");
                }
                for (int i = this.commas.size() + 1; i < num; i++) {
                    String tmp = "%_" + cnt.use();
                    // %3 = getelementptr i32, i32* @a, i32 0
                    sb.append("   " + tmp + " = getelementptr " + ptrType + ", "
                            + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + i + "\n");
                    // store i32 1, i32* %2
                    sb.append("   store " + iType + " " + "0" + ", " + iType + "* " + tmp + "\n");
                }
            } else {
                for (int i = 1; i < num; i++) {
                    String tmp = "%_" + cnt.use();
                    // %3 = getelementptr i32, i32* @a, i32 0
                    sb.append("   " + tmp + " = getelementptr " + ptrType + ", "
                            + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + i + "\n");
                    // store i32 1, i32* %2
                    sb.append("   store " + iType + " " + "0" + ", " + iType + "* " + tmp + "\n");
                }
            }
        } else {
            for (int i = 0; i < num; i++) {
                String tmp = "%_" + cnt.use();
                // %3 = getelementptr i32, i32* @a, i32 0
                sb.append("   " + tmp + " = getelementptr " + ptrType + ", "
                        + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + i + "\n");
                // store i32 1, i32* %2
                sb.append("   store " + iType + " " + "0" + ", " + iType + "* " + tmp + "\n");
            }
        }
        return sb.toString();
    }

    public String toI8(String res, String iType, Counter cnt, ArrayList<String> strings) {
        if (Counter.getType(res).equals("i32") && iType.equals("i8")) {
            String tmp = "%_" + cnt.use();
            // %10 = trunc i32 %9 to i8
            strings.add("   " + tmp + " = trunc i32 " + res + " to i8\n");
            Counter.put(tmp, "i8");
            return tmp;
        }
        return res;
    }
}
