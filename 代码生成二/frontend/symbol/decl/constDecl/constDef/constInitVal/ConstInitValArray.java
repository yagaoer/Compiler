package frontend.symbol.decl.constDecl.constDef.constInitVal;

import frontend.lexer.Token;
import frontend.symbol.exp.ConstExp;
import llvm.Counter;

import java.util.ArrayList;

public class ConstInitValArray implements ConstInitValFactor {
    private Token lBrace; // '{'
    private ConstExp constExp;
    private ArrayList<Token> commas;
    private ArrayList<ConstExp> constExps; // [ ConstExp { ',' ConstExp } ]
    private Token rBrace; // '}'

    public ConstInitValArray(Token lBrace, ConstExp constExp, ArrayList<Token> commas,
                             ArrayList<ConstExp> constExps, Token rBrace) {
        this.lBrace = lBrace;
        this.constExp = constExp;
        this.commas = commas;
        this.constExps = constExps;
        this.rBrace = rBrace;
    }

    public String globalArrayInit(int num, String iType) {
        StringBuilder sb = new StringBuilder();
        if (this.constExp != null) {
            sb.append("[").append(iType).append(" ").append(constExp.toRes());
            if (this.commas != null && this.constExps != null
                    && this.commas.size() == this.constExps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    sb.append(", ").append(iType).append(" ").append(this.constExps.get(i).toRes());
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
            return this.constExp.toRes();
        } else {
            return this.constExps.get(index - 1).toRes();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lBrace.toString());
        if (this.constExp != null) {
            sb.append(this.constExp.toString());
            if (this.commas != null && this.constExps != null
                    && this.commas.size() == this.constExps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    sb.append(this.commas.get(i).toString()).append(this.constExps.get(i).toString());
                }
            }
        }
        sb.append(this.rBrace.toString());
        return sb.toString();
    }

    public String localArrayInit(Counter cnt, int num, String name, String ptrType, String iType, ArrayList<String> strings) {
        StringBuilder sb = new StringBuilder();
        if (this.constExp != null) {
            String ptr = "%_" + cnt.use();
            // %3 = getelementptr i32, i32* @a, i32 0
            sb.append("   " + ptr + " = getelementptr " + ptrType + ", "
                    + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + 0 + "\n");
            String res = toI8(constExp.toInstructions(cnt, iType, strings), iType, cnt, strings);
            // store i32 1, i32* %2
            sb.append("   store " + iType + " " + res + ", "
                    + iType + "* " + ptr + "\n");
            if (this.commas != null && this.constExps != null
                    && this.commas.size() == this.constExps.size()) {
                for (int i = 0; i < this.commas.size(); i++) {
                    String tmp = "%_" + cnt.use();
                    // %3 = getelementptr i32, i32* @a, i32 0
                    sb.append("   " + tmp + " = getelementptr " + ptrType + ", "
                            + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + (i+1) + "\n");
                    String res0 = toI8(constExps.get(i).toInstructions(cnt, iType, strings), iType, cnt, strings);
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
