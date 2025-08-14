package frontend.symbol.decl;

import frontend.lexer.Token;
import frontend.symbol.decl.constDecl.constDef.constInitVal.ConstInitValFactor;
import frontend.symbol.decl.varDecl.varDef.varInitVal.VarInitValFactor;
import llvm.Counter;

import java.util.ArrayList;

public class StringConst implements VarInitValFactor, ConstInitValFactor {
    private Token token; // 标识符单词

    public StringConst(Token token) {
        this.token = token;
    }

    public String llvmArrayInit(int num, String iType) {
        char [] chars = getFormatCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(iType).append(" ").append((int)chars[0]);
        for (int i = 1; i < chars.length; i++) {
            sb.append(", ").append(iType).append(" ").append((int)chars[i]);
        }
        for (int i = chars.length; i < num; i++) {
            sb.append(", ").append(iType).append(" ").append("0");
        }
        sb.append("]");
        return sb.toString();
    }

    public int toRes(int index) {
        char [] chars = getFormatCharArray();
        return chars[index];
    }

    @Override
    public String toString() {
        return this.token.toString();
    }

    public Token getToken() {
        return token;
    }

    public char[] getFormatCharArray() {
        char[] s = token.getName().substring(1, token.getName().length() - 1).toCharArray();
        ArrayList<java.lang.Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '\\') {
                int out = 92;
                i++;
                switch (s[i]) {
                    case 'a' -> out = 7; // \a 007
                    case 'b' -> out = 8; // \b 008
                    case 't' -> out = 9; // \t 009
                    case 'n' -> out = 10; // \n 010
                    case 'v' -> out = 11; // \v 011
                    case 'f' -> out = 12; // \f 012
                    case '"' -> out = 34; // \" 034
                    case '\'' -> out = 39; // \' 039
                    case '\\' -> out = 92; // \\ 092
                    case '0' -> out = 0; // \0 000
                    default -> i--;
                }
                chars.add((char)out);
            } else {
                chars.add(s[i]);
            }
        }
        char[] res = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) {
            res[i] = chars.get(i);
        }
        return res;
    }

    public String localArrayInit(Counter cnt, int num, String name, String ptrType, String iType) {
        char [] chars = getFormatCharArray();
        StringBuilder sb = new StringBuilder();
        // %3 = getelementptr i32, i32* @a, i32 0
        String ptr = "%_" + cnt.use();
        sb.append("   " + ptr + " = getelementptr " + ptrType + ", "
                + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + 0 + "\n");
        // store i32 1, i32* %2
        sb.append("   store " + iType + " " + (int)chars[0] + ", "
                + iType + "* " + ptr + "\n");
        for (int i = 1; i < chars.length; i++) {
            String tmp = "%_" + cnt.use();
            // %3 = getelementptr i32, i32* @a, i32 0
            sb.append("   " + tmp + " = getelementptr " + ptrType + ", "
                    + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + i + "\n");
            // store i32 1, i32* %2
            sb.append("   store " + iType + " " + (int)chars[i] + ", "
                    + iType + "* " + tmp + "\n");
        }
        for (int i = chars.length; i < num; i++) {
            String tmp = "%_" + cnt.use();
            // %3 = getelementptr i32, i32* @a, i32 0
            sb.append("   " + tmp + " = getelementptr " + ptrType + ", "
                    + ptrType + "* " + "%" + name + ", " + "i32 0" + ", " + "i32 " + i + "\n");
            // store i32 1, i32* %2
            sb.append("   store " + iType + " " + 0 + ", "
                    + iType + "* " + tmp + "\n");
        }
        return sb.toString();
    }
}
