import frontend.symbol.CompUnit;
import frontend.symbol.CompUnitParser;
import frontend.lexer.Lexer;
import frontend.lexer.Token;
import llvm.LLVMModule;
import llvm.LLVMParser;

import java.io.*;

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

        String outputFileName = "llvm_ir.txt";
        Token.tokens = lexer.getTokens();
        CompUnitParser compUnitParser = new CompUnitParser(lexer.getTokens());
        CompUnit compUnit = compUnitParser.parseCompUnit();
        if (Token.getErrors().isEmpty()) {
            LLVMParser llvmParser = new LLVMParser(compUnit);
            LLVMModule llvmModule = llvmParser.parseLLVMModule();
            try {
                OutputStream outputStream = new FileOutputStream(outputFileName);
                //outputStream.write(lexer.getTokensString().getBytes());
                //outputStream.write(compUnit.toString().getBytes());
                //outputStream.write(SymbolTable.getString().getBytes());
                outputStream.write(llvmModule.getString().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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