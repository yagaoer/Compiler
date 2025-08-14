package frontend;

public class Token {
    private String name;
    private int line;
    private String code;

    public Token(String name, int line, String code) {
        this.name = name;
        this.line = line;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.code + " " + this.name + "\n";
    }

    public String toError() {
        return this.line + " " + this.code;
    }
}
