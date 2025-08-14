package frontend;

import java.util.regex.Pattern;

public enum Error {
    a("^&|\\|");

    private final Pattern pattern;
    Error(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
