package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor;

import frontend.symbol.SymbolCode;
import llvm.Counter;

import java.util.ArrayList;

public interface UnaryExpFactor {
    String toString();

    SymbolCode getSymbolCode();

    int toRes();

    String toInstructions(Counter cnt, String iType, ArrayList<String> strings);
}
