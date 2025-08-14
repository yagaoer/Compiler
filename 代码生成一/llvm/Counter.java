package llvm;

import java.util.HashMap;

public class Counter {
    private static HashMap<String, String> cntTypes = new HashMap<>();
    private int cnt;

    public Counter() {
        this.cnt = 0;
    }

    public int use() {
        return cnt++;
    }

    public static void put(String name, String type) {
        cntTypes.put(name, type);
    }

    public static String getType(String name) {
        return cntTypes.get(name);
    }
}
