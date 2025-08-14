import frontend.CompUnit;
import frontend.CompUnitParser;
import frontend.Lexer;
import frontend.Token;

import java.io.*;
import java.util.ArrayList;

public class Compiler {
    public static void main(String[] args) {
        String inputFileName = "testfile.txt";
        InputStream inputStream;
        try {
             inputStream = new FileInputStream(inputFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Lexer lexer = new Lexer(inputStream);

        String outputFileName = "parser.txt";
        Token.tokens = lexer.getTokens();
        CompUnitParser compUnitParser = new CompUnitParser(lexer.getTokens());
        CompUnit compUnit = compUnitParser.parseCompUnit();
        try {
            OutputStream outputStream = new FileOutputStream(outputFileName);
            //outputStream.write(lexer.getTokensString().getBytes());
            outputStream.write(compUnit.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String errorFileName = "error.txt";
        try {
            OutputStream outputStream = new FileOutputStream(errorFileName);
            outputStream.write(Token.getErrors().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}