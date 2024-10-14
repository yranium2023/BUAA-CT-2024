package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

/**
 * @author 吴鹄远
 * @Description
 * 数值 Number → IntConst
 * @date 2024/10/9 21:14
 */
public class Number {
    private Token intConst=null;
    private static Number instance=new Number();
    private Number(){

    }
    public static Number getInstance(){
        return instance;
    }
    public Number parseNumber(){
        Number number=new Number();
        number.intConst=Global.parser.match(LexType.INTCON);
        return number;
    }
}
