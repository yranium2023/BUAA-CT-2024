package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 字符 Character → CharConst
 * @date 2024/10/9 21:14
 */
public class Character {
    private static final String name="<Character>";
    private Token charConst=null;
    private static Character instance=new Character();
    private Character(){

    }
    public static Character getInstance(){
        return instance;
    }
    public Character parseCharacter(){
        Character character=new Character();
        character.charConst= Global.parser.match(LexType.CHRCON);
        return character;
    }
    public void print() throws IOException {
        charConst.print();
        Global.parser.out.write(name+"\n");
    }
}
