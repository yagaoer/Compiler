package frontend.parser;

import frontend.lexer.Character;
import frontend.lexer.Token;

import java.util.ArrayList;

public class CharacterParser {
    private ArrayList<Token> tokens;

    public CharacterParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Character parseCharacter() {

        Token charConst = tokens.remove(0);

        return new Character(charConst);
    }
}
