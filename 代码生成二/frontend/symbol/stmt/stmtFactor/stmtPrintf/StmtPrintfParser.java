package frontend.symbol.stmt.stmtFactor.stmtPrintf;

import frontend.lexer.TokenCode;
import frontend.symbol.exp.ExpParser;
import frontend.symbol.decl.StringConstParser;
import frontend.symbol.SymbolTable;
import frontend.symbol.exp.Exp;
import frontend.symbol.decl.StringConst;
import frontend.lexer.Token;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StmtPrintfParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public StmtPrintfParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }

    public StmtPrintf parseStmtPrintf() {

        Token printf = tokens.remove(0);

        Token lParent = tokens.remove(0);

        StringConst stringConst = new StringConstParser(tokens).parseStringConst();

        ArrayList<Token> commmas = new ArrayList<>();
        ArrayList<Exp> exps = new ArrayList<>();
        while (tokens.get(0).getCode().equals(TokenCode.COMMA)) {

            commmas.add(tokens.remove(0));

            exps.add(new ExpParser(tokens, symbolTable).parseExp());
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        Token semicn = null;
        if (tokens.get(0).getCode().equals(TokenCode.SEMICN)) {
            semicn = tokens.remove(0);
        } else {
            semicn = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.SEMICN);
        }

        lError(printf, stringConst, exps);
        return new StmtPrintf(printf, lParent, stringConst, commmas, exps, rParent, semicn);
    }

    private void lError(Token printf, StringConst stringConst, ArrayList<Exp> exps) {
        Pattern pattern = Pattern.compile("%[cd]");
        Matcher matcher = pattern.matcher(stringConst.getToken().getName());
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        if (count != exps.size()) {
           new Token("l", printf.getLine());
        }
    }
}
