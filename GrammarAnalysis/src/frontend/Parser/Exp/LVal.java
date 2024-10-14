package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 左值表达式 LVal → Ident ['[' Exp ']']
 * //存在 k 类错误
 * @date 2024/10/9 21:13
 */
public class LVal {
    private Token Ident=null;
    private Token leftBracket=null;
    private Exp exp=null;
    private Token rightBracket=null;
    private static LVal instance=new LVal();
    public static LVal getInstance(){
        return instance;
    }
    public LVal parseLVal(){
        LVal lVal=new LVal();
        lVal.Ident= Global.parser.match(LexType.IDENFR);
        Token token=Global.parser.getNextToken();
        Global.parser.unReadPrevToken();
        if (token.getType().equals(LexType.LBRACK)){
            lVal.leftBracket=Global.parser.match(LexType.LBRACK);
            lVal.exp=Exp.getInstance().parseExp();
            lVal.rightBracket=Global.parser.match(LexType.RBRACK);
        }
        return lVal;
    }

}
