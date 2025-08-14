package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor;

import frontend.symbol.Symbol;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.func.funcParam.FuncRParams;
import frontend.symbol.Ident;
import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;

public class UnaryExpFuncR implements UnaryExpFactor {
    /*
     Ident '(' [FuncRParams] ')'
     */
    private Ident ident; //  Ident
    private Token lParent; // '('
    private FuncRParams funcRParams; // [FuncRParams]
    private Token rParent; // ')'
    private SymbolCode symbolCode;
    private SymbolTable symbolTable;

    public UnaryExpFuncR(Ident ident, Token lParent, FuncRParams funcRParams, Token rParent, SymbolCode symbolCode, SymbolTable symbolTable) {
        this.ident = ident;
        this.lParent = lParent;
        this.funcRParams = funcRParams;
        this.rParent = rParent;
        this.symbolCode = symbolCode;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString()).append(lParent.toString());
        if (funcRParams != null) {
            sb.append(this.funcRParams.toString());
        }
        sb.append(rParent.toString());
        return sb.toString();
    }

    @Override
    public SymbolCode getSymbolCode() {
        return symbolCode;
    }

    @Override
    public int toRes() {
        return 0;
    }

    @Override
    public String toInstructions(Counter cnt, String iType, ArrayList<String> strings) {
        Symbol symbol = symbolTable.getSymbolCheck(this.ident.getToken().getName());
//        System.out.print(symbol.getName());
        String func = "@" + symbol.getName();
        iType = "i32";
        if (symbol.getSymbolCode().name().contains("Char")) {
            iType = "i8";
        }
//        System.out.print(func + "\n");
        String params = "";
        if (this.funcRParams != null) {
            params = this.funcRParams.toInstructions(cnt, iType, strings, symbol.getParams(), symbol.getFuncFParams());
        }
        if (symbol.getSymbolCode().equals(SymbolCode.VoidFunc)) {
            strings.add("   call void " + func + "(" + params + ")" + "\n");
            return "";
        }
        // %6 = call i32 @foo(i32 %4, i32 %5)
        String tmp = "%_" + cnt.use();
        strings.add("   " + tmp + " = call " + "i32" + " " + func + "(" + params + ")" + "\n");
        String res;
        if (iType.equals("i8")) {
            String res0 = "%_" + cnt.use();
            // %10 = trunc i32 %9 to i8
            strings.add("   " + res0 + " = trunc i32 " + tmp + " to i8\n");
            res = "%_" + cnt.use();
            // %8 = zext i8 %7 to i32
            strings.add("   " + res + " = zext " + iType + " " + res0 + " to " + "i32" + "\n");
        } else {
            res = tmp;
        }
        Counter.put(res, "i32");
        return res;
    }
}
