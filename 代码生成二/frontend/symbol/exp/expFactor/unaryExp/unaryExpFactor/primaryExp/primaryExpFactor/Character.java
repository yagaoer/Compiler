package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor;

import frontend.symbol.SymbolCode;
import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class Character implements PrimaryExpFactor {
    private final String name = "<Character>";
    private Token charConst;

    public Character(Token charConst) {
        this.charConst = charConst;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.charConst.toString()).append(this.name).append("\n");
        return sb.toString();
    }

    @Override
    public SymbolCode getSymbolCode() {
        return SymbolCode.ConstChar;
    }

    @Override
    public int toRes() {
        return getCharConst();
    }

    public int  getCharConst() {
        char[] s = charConst.getName().substring(1, charConst.getName().length() - 1).toCharArray();
        if (s.length == 1) {
            return s[0];
        } else  {
            int out = 92;
            switch (s[1]) {
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
            }
            return out;
        }
    }

    @Override
    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        int res = getCharConst();
        Counter.put(res +"", "i32");
        return res + "";
    }
}
