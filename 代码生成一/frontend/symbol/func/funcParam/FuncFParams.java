package frontend.symbol.func.funcParam;

import frontend.symbol.Symbol;
import frontend.lexer.Token;
import llvm.Counter;

import java.util.ArrayList;
import java.util.HashMap;

public class FuncFParams {
    private final String name = "<FuncFParams>";
    private FuncFParam funcFParam; // FuncFParam
    private ArrayList<Token> commas;
    private ArrayList<FuncFParam> funcFParams; //  { ',' FuncFParam }

    public FuncFParams(FuncFParam funcFParam, ArrayList<Token> commas, ArrayList<FuncFParam> funcFParams) {
        this.funcFParam = funcFParam;
        this.commas = commas;
        this.funcFParams = funcFParams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.funcFParam.toString());
        if (this.commas != null && this.funcFParams != null &&
                this.commas.size() == this.funcFParams.size()) {
            for (int i = 0; i < this.commas.size(); i++) {
                sb.append(this.commas.get(i).toString()).append(this.funcFParams.get(i).toString());
            }
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }

//    public void parseLLVMParams(SymbolTable symbolTable, ArrayList<Symbol> symbols) {
//        if (this.commas != null && this.funcFParams != null &&
//                this.commas.size() == this.funcFParams.size()) {
//            for (int i = 0; i < this.commas.size(); i++) {
//                this.funcFParams.get(i).parseLLVMParm(symbolTable, symbols);
//            }
//        }
//    }

    public String getString(Counter cnt, HashMap<Symbol, String> params) {
        // i32 %0, i32* %1
        String res = funcFParam.getString(cnt, params);
        if (this.commas != null && this.funcFParams != null &&
                this.commas.size() == this.funcFParams.size()) {
            for (int i = 0; i < this.commas.size(); i++) {
                res += ", " + this.funcFParams.get(i).getString(cnt, params);
            }
        }
        return res;
    }

//    public void toInstructions(Integer cnt, String iType, ArrayList<String> strings) {
//        // i32 %0, i32* %1
//        funcFParam.toInstructions(cnt, iType, strings);
//        if (this.commas != null && this.funcFParams != null &&
//                this.commas.size() == this.funcFParams.size()) {
//            for (int i = 0; i < this.commas.size(); i++) {
//                this.funcFParams.get(i).toInstructions(cnt, iType, strings);
//            }
//        }
//    }
}
