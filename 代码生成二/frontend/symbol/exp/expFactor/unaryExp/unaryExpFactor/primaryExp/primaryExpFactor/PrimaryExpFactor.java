package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor.primaryExp.primaryExpFactor;

import frontend.symbol.SymbolCode;
import llvm.Counter;

import java.util.ArrayList;

public interface PrimaryExpFactor {
    String toString();

    SymbolCode getSymbolCode();

    int toRes();

    String toInstructions(Counter cnt, String iType, ArrayList<String> strings);
}
