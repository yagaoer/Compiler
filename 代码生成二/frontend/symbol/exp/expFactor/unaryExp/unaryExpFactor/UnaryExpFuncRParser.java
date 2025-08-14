package frontend.symbol.exp.expFactor.unaryExp.unaryExpFactor;

import frontend.lexer.*;
import frontend.symbol.func.funcParam.FuncRParamsParser;
import frontend.symbol.IdentParser;
import frontend.symbol.Ident;
import frontend.symbol.Symbol;
import frontend.symbol.SymbolCode;
import frontend.symbol.SymbolTable;
import frontend.symbol.exp.Exp;
import frontend.symbol.func.funcParam.FuncRParams;

import java.util.ArrayList;

public class UnaryExpFuncRParser {
    private ArrayList<Token> tokens;
    private SymbolTable symbolTable;

    public UnaryExpFuncRParser(ArrayList<Token> tokens, SymbolTable symbolTable) {
        this.tokens = tokens;
        this.symbolTable = symbolTable;
    }


    public UnaryExpFuncR parseUnaryFuncRExp() {

        Ident ident = new IdentParser(tokens).parseIdent();
        Symbol symbol = this.symbolTable.getSymbol(ident.getToken().getName());

        Token lParent = tokens.remove(0);

        FuncRParams funcRParams = null;
        if (tokens.get(0).getCode().equals(TokenCode.IDENFR) ||
                tokens.get(0).getCode().equals(TokenCode.INTCON) ||
                tokens.get(0).getCode().equals(TokenCode.CHRCON) ||
                tokens.get(0).getCode().equals(TokenCode.LPARENT) ||
                tokens.get(0).getCode().equals(TokenCode.PLUS) ||
                tokens.get(0).getCode().equals(TokenCode.MINU) ||
                tokens.get(0).getCode().equals(TokenCode.NOT)) {
            funcRParams = new FuncRParamsParser(tokens, symbolTable).parseFuncRParams();
        }

        Token rParent = null;
        if (tokens.get(0).getCode().equals(TokenCode.RPARENT)) {
            rParent = tokens.remove(0);
        } else {
            rParent = new Token(Token.tokens.get(Token.tokens.indexOf(tokens.get(0)) - 1), TokenCode.RPARENT);
        }

        // c
        SymbolCode symbolCode = SymbolCode.VoidFunc;
        if (!cError(ident)) {
            symbolCode = this.symbolTable.getSymbol(ident.getToken().getName()).getSymbolCode();
            if (symbolCode.equals(SymbolCode.IntFunc)) {
                symbolCode = SymbolCode.Int;
            } else if (symbolCode.equals(SymbolCode.CharFunc)) {
                symbolCode = SymbolCode.Char;
            }
            // d
            dError(ident, funcRParams);
            // e
            eError(ident, funcRParams);
        }

        return new UnaryExpFuncR(ident, lParent, funcRParams, rParent, symbolCode, symbolTable);
    }

    private boolean cError(Ident ident) {
        if (this.symbolTable.cError(ident.getToken().getName())) {
            new Token("c", ident.getToken().getLine());
            return true;
        }
        return false;
    }

    private void dError(Ident ident, FuncRParams funcRParams) {
        Symbol symbol = this.symbolTable.getSymbol(ident.getToken().getName());
        if (symbol.getSymbolCode().equals(SymbolCode.CharFunc) ||
                symbol.getSymbolCode().equals(SymbolCode.VoidFunc) ||
                symbol.getSymbolCode().equals(SymbolCode.IntFunc)) {
            ArrayList<Symbol> symbols = symbol.getFuncFParams();

            if (funcRParams == null && symbols.isEmpty()) {
                return;
            }
            if (funcRParams != null && symbols.size() == funcRParams.size()) {
                return;
            }
            new Token("d", ident.getToken().getLine());
        }
    }

    private void eError(Ident ident, FuncRParams funcRParams) {
        Symbol symbol = this.symbolTable.getSymbol(ident.getToken().getName());
        if (symbol.getSymbolCode().equals(SymbolCode.CharFunc) ||
                symbol.getSymbolCode().equals(SymbolCode.VoidFunc) ||
                symbol.getSymbolCode().equals(SymbolCode.IntFunc)) {
            ArrayList<Symbol> symbols = symbol.getFuncFParams();
            if (funcRParams != null && symbols.size() == funcRParams.size()) {
                ArrayList<Exp> exps = funcRParams.getExps();
                for (int i = 0; i < symbols.size(); i++) {
                    if (!exps.get(i).symbolCodeEquals(symbols.get(i).getSymbolCode())) {
                        new Token("e", ident.getToken().getLine());
                        break;
                    }
                }
            }
        }
    }
}
