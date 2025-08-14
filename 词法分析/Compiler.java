import frontend.Lexer;

import java.io.*;

public class Compiler {
    public static void main(String[] args) {
        String inputFileName = "testfile.txt";
        InputStream inputStream = null;
        try {
             inputStream = new FileInputStream(inputFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Lexer lexer = new Lexer(inputStream);

        String outputFileName = "lexer.txt";
        try {
            OutputStream outputStream = new FileOutputStream(outputFileName);
            outputStream.write(lexer.getTokensString().getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String errorFileName = "error.txt";
        try {
            OutputStream errorStream = new FileOutputStream(errorFileName);
            errorStream.write(lexer.getErrorsString().getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}