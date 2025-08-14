package frontend;

import java.util.ArrayList;

public class FuncFParamsParser {
    private ArrayList<Token> tokens;

    public FuncFParamsParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public FuncFParams parseFuncFParams() {

        FuncFParam funcFParam = new FuncFParamParser(tokens).parseFuncFParam();

        ArrayList<Token> commas = new ArrayList<>();
        ArrayList<FuncFParam> funcFParams = new ArrayList<>();
        while (tokens.get(0).getCode().equals(Code.COMMA)) {

            commas.add(tokens.remove(0));

            funcFParams.add(new FuncFParamParser(tokens).parseFuncFParam());
        }

        return new FuncFParams(funcFParam, commas, funcFParams);
    }
}
