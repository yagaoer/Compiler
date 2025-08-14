package frontend;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Token implements Comparable<Token> {
    public static ArrayList<Token> tokens;
    private String name;
    private int line;
    private Code code;
    private static ArrayList<Token> errors = new ArrayList<>();

    private String error;

    public Token(String name, int line, Code code) {
        this.name = name;
        this.line = line;
        this.code = code;
    }

    public Token(Token pretoken, Code code) {
        this.line = pretoken.line;;
        if (code.equals(Code.SEMICN)) {
            this.error = "i";
        } else if (code.equals(Code.RPARENT)) {
            this.error = "j";
        } else {
            this.error = "k";
        }
        this.toError();
    }

    public Token(String name, int line) {
        if (name.equals("&")) {
            this.name = "&&";
            this.code = Code.AND;
        } else {
            this.name = "||";
            this.code = Code.OR;
        }
        this.error = "a";
        this.line = line;
        toError();
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public Code getCode() {
        return code;
    }

    @Override
    public String toString() {
        if (this.code != null) {
            return this.code.toString() + " " + this.name + "\n";
        }
       return "Error\n";
    }

    public void toError() {
        Token.errors.add(this);
    }

    public static String getErrors() {
        Collections.sort(Token.errors);
        StringBuilder sb = new StringBuilder();
        for (Token token : Token.errors) {
            String error = token.line + " " + token.error + "\n";
            sb.append(error);
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Token o) {
        return Integer.compare(this.line, o.line);
    }
}
