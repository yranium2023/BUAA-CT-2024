package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Exp.ConstExp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 常量定义 ConstDef → Ident [ '[' ConstExp ']' ] '=' ConstInitVal
 * 存在 k 类错误
 * @date 2024/10/9 21:11
 */
public class ConstDef {
    private Token Ident=null; //Ident
    private Token leftBracket=null; //'['
    private ConstExp constExp=null;
    private Token rightBracket=null; //']'
    private Token eq=null; //'='
    private ConstInitVal constInitVal=null;
    private ConstDef() {
    }
    private static ConstDef instance=new ConstDef();

    public static ConstDef getInstance(){
        return instance;
    }

    public ConstDef parseConstDef(){
        ConstDef constDef=new ConstDef();//空白的ConstDef
        constDef.Ident=Global.parser.match(LexType.IDENFR);
        Token token=Global.parser.preReadToken();
        if(token.getType().equals(LexType.LBRACK)){
            //一维数组
            constDef.leftBracket=Global.parser.getNextToken();
            constDef.constExp=ConstExp.getInstance().parseConstExp();
            constDef.rightBracket=Global.parser.match(LexType.RBRACK);
        }
        //常表达式
        constDef.eq=Global.parser.match(LexType.ASSIGN);
        constDef.constInitVal=ConstInitVal.getInstance().parseConstInitVal();
        return constDef;
    }
}
