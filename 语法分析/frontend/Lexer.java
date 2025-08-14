package frontend;

import javax.swing.*;
import java.io.*;
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
            if (matcherBlockCommentEnd.find() && blockComment) {
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
                        java.lang.Character.isWhitespace(rawLine.charAt(start))) {
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
                Pattern patternBlockCommentEnd1 = Pattern.compile(".*\\*/");
                Matcher matcherBlockCommentEnd1 = patternBlockCommentEnd1.matcher(line);
                if (matcherBlockCommentStart.find()) {
                    blockComment = true;
                    if (matcherBlockCommentEnd1.find()) {
                        blockComment = false;
                        start += matcherBlockCommentEnd1.end();
                        line = rawLine.substring(start);
                    } else {
                        break;
                    }
                }

                // 单词及错误匹配
                for (Code code : Code.values()) {
                    Pattern pattern = code.getPattern();
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.find()) {
//                        if (code.equals(Code.STRCON)) {
//                            for (int i = matcher.start() + 1; i < line.length(); i++) {
//                                if (line.charAt(i) == '"' && line.charAt(i - 1) != '\\') {
//                                    tokens.add(new Token(line.substring(matcher.start(), i + 1), lineNumber, code));
//                                    start  = start + i + 1;
//                                    break;
//                                }
//                            }
//                        } else if (code.equals(Code.CHRCON)) {
//                            for (int i = matcher.start() + 1; i < line.length(); i++) {
//                                if (line.charAt(i) == '\'' && line.charAt(i - 1) != '\\') {
//                                    tokens.add(new Token(line.substring(matcher.start(), i + 1), lineNumber, code));
//                                    start  = start + i + 1;
//                                    break;
//                                }
//                            }
//                        } else {
//                            tokens.add(new Token(matcher.group(0), lineNumber, code));
//                            start += matcher.end();
//                        }
                        tokens.add(new Token(matcher.group(0), lineNumber, code));
                        start += matcher.end();
                        line = rawLine.substring(start);
                        error = false;
                        break;
                    }
                }

                // 否则进行错误输出
                if (error) {
                    for (Error error1: Error.values()) {
                        Pattern pattern = error1.getPattern();
                        Matcher matcher = pattern.matcher(line);

                        if (matcher.find()) {
                            tokens.add(new Token(matcher.group(0), lineNumber));
                            start += matcher.end();
                            break;
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Token> getTokens() {
        return (ArrayList<Token>) tokens.clone();
    }

    public String getTokensString() {
        StringBuilder res = new StringBuilder();
        for (Token token : tokens) {
            res.append(token.toString());
        }
        return res.toString().trim();
    }
}
