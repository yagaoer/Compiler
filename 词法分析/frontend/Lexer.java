package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private InputStream inputStream;
    private ArrayList<String> lines;
    private ArrayList<Token> tokens;
    private ArrayList<Token> errors;

    public Lexer(InputStream inputStream) {
        this.inputStream = inputStream;
        this.lines = new ArrayList<>();
        this.tokens = new ArrayList<>();
        this.errors = new ArrayList<>();
        getLines();
        linesLexer();
    }

    private void getLines() {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        // 将Reader包装进BufferedReader
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        try {
            // 逐行读取
            while ((line = bufferedReader.readLine()) != null) {
                // 处理每一行的字符串
                //System.out.println("读取到的行: " + line);
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流以释放资源
            try {
                bufferedReader.close();
                inputStreamReader.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void linesLexer() {
        Boolean blockComment = false;

        for (String rawLine : lines) {

            int start = 0;
            int lineNumber = lines.indexOf(rawLine) + 1;
            String line = rawLine.substring(start);

            // 多行注释结束
            Pattern patternBlockCommentEnd = Pattern.compile(".*\\*/");
            Matcher matcherBlockCommentEnd = patternBlockCommentEnd.matcher(line);
            if (matcherBlockCommentEnd.find()) {
                blockComment = false;
                start += matcherBlockCommentEnd.end();
            }
            if (blockComment) {
                continue;
            }

            while (start < rawLine.length()) {
                Boolean error = true;

                // 空格
                while (start < rawLine.length() &&
                        Character.isWhitespace(rawLine.charAt(start))) {
                    start += 1;
                }
                line = rawLine.substring(start);

                // 单行注释
                Pattern patternLineComment = Pattern.compile("^//.*");
                Matcher matcherLineComment = patternLineComment.matcher(line);
                if (matcherLineComment.find()) {
                    break;
                }

                // 多行注释开始
                Pattern patternBlockCommentStart = Pattern.compile("^/\\*.*");
                Matcher matcherBlockCommentStart = patternBlockCommentStart.matcher(line);
                if (matcherBlockCommentStart.find()) {
                    blockComment = true;
                    break;
                }

                // 单词匹配
                for (Constant constant: Constant.values()) {
                    Pattern pattern = constant.getPattern();
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.find()) {
                        tokens.add(new Token(matcher.group(0), lineNumber, constant.name()));
                        start += matcher.end();
                        line = rawLine.substring(start);
                        error = false;
                        break;
                    }


                }

                // 否则进行错误匹配
                if (error) {
                    for (Error Error: Error.values()) {
                        Pattern pattern = Error.getPattern();
                        Matcher matcher = pattern.matcher(line);

                        if (matcher.find()) {
                            errors.add(new Token(matcher.group(0), lineNumber, Error.name()));
                            start += matcher.end();
                            break;
                        }
                    }
                }
            }
        }
    }

    public String getTokensString() {
        StringBuilder res = new StringBuilder();
        for (Token token : tokens) {
            res.append(token.toString());
        }
        return res.toString().trim();
    }

    public String getErrorsString() {
        StringBuilder res = new StringBuilder();
        for (Token token : errors) {
            res.append(token.toError());
        }
        return res.toString().trim();
    }
}
