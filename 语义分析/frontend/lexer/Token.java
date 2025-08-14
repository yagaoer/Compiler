package frontend.lexer;

import frontend.TokenCode;

import java.util.ArrayList;
import java.util.Collections;

public class Token implements Comparable<Token> {
    public static ArrayList<Token> tokens;
    private String name;
    private int line;
    private TokenCode tokenCode;
    private static ArrayList<Token> errors = new ArrayList<>();

    private String error;

    public Token(String name, int line, TokenCode tokenCode) {
        this.name = name;
        this.line = line;
        this.tokenCode = tokenCode;
    }

    public Token(Token pretoken, TokenCode tokenCode) {
        this.line = pretoken.line;;
        if (tokenCode.equals(TokenCode.SEMICN)) {
            this.error = "i";
        } else if (tokenCode.equals(TokenCode.RPARENT)) {
            this.error = "j";
        } else {
            this.error = "k";
        }
        this.toError();
    }

    public Token(String name, int line) {
        this.error = name;
        if (name.equals("&")) {
            this.name = "&&";
            this.tokenCode = TokenCode.AND;
            this.error = "a";
        } else if (name.equals("|")){
            this.name = "||";
            this.tokenCode = TokenCode.OR;
            this.error = "a";
        }
        this.line = line;
        toError();
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public TokenCode getCode() {
        return tokenCode;
    }

    @Override
    public String toString() {
        if (this.tokenCode != null) {
            return this.tokenCode.toString() + " " + this.name + "\n";
        }
       return "Error\n";
    }

    public void toError() {
//        if (!this.error.equals("c") && !this.error.equals("e")) {
//            Token.errors.add(this);
//        }
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
