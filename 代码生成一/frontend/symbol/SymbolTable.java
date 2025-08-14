package frontend.symbol;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTable {
    private static HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
    private HashMap<String, Symbol> symbols;
    private SymbolTable parent;
    private static ArrayList<SymbolTable> symbolTables = new ArrayList<>();
    private ArrayList<Symbol> symbolArrayList;
    private boolean inCycle = false;
    private int field = 1;

    public SymbolTable(SymbolTable parent) {
        this.symbols = new HashMap<>();
        this.symbolArrayList = new ArrayList<>();
        this.parent = parent;
        if (parent != null) {
            this.inCycle = parent.isInCycle();
            this.field = parent.getField();
        }
    }

    public boolean isGlobal() {
        return this.parent == null;
    }

    public Symbol getSymbol(String name) {
        if (this.symbols.containsKey(name)) {
            return this.symbols.get(name);
        }
        if (this.parent != null) {
            return this.parent.getSymbol(name);
        }
        return null;
    }

    public Symbol getSymbolCheck(String name) {
        if (this.symbols.containsKey(name)) {
            Symbol symbol = this.symbols.get(name);
            if (symbol.getLlvmType() != null) {
                return symbol;
            }
        }
        if (this.parent != null) {
            return this.parent.getSymbol(name);
        }
        return null;
    }

    public void addSymbol(Symbol symbol) {
        this.symbols.put(symbol.name, symbol);
        this.symbolArrayList.add(symbol);
    }

    public boolean bError(String name) {
        for (String haved : this.symbols.keySet()) {
            if (haved.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean cError(String name) {
        for (String haved : this.symbols.keySet()) {
            if (haved.equals(name)) {
                return false;
            }
        }
        if (this.parent != null) {
            return this.parent.cError(name);
        } else {
            return true;
        }
    }

    public void setInCycle(boolean inCycle) {
        this.inCycle = inCycle;
    }

    public boolean isInCycle() {
        return inCycle;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
        symbolTables.add(this);
    }

    public static String getString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < symbolTables.size(); i++) {
            for (int j = 0; j < symbolTables.get(i).symbolArrayList.size(); j++) {
                Symbol symbol = symbolTables.get(i).symbolArrayList.get(j);
                if (!symbol.getName().equals("main")) {
                    sb.append(i + 1 + " " + symbol.getName() + " " + symbol.getSymbolCode() + "\n");
                }
            }
        }
        return sb.toString();
    }
}
