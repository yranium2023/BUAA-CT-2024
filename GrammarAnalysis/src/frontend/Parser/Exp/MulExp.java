package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 乘除模表达式 MulExp → UnaryExp | MulExp ('*' | '/' | '%') UnaryExp
 * 转化为右递归的形式为：MulExp → UnaryExp ｜ UnaryExp ('*' | '/' | '%') MulExp
 * @date 2024/10/9 21:14
 */
public class MulExp {
    private static MulExp instance=new MulExp();
    private UnaryExp firstUnaryExp=null;
    private Token operator=null;
    private MulExp mulExp=null;
    private MulExp(){

    }
    public static MulExp getInstance(){
        return instance;
    }
    public MulExp parseMulExp(){
        MulExp mulExp1=new MulExp();
        mulExp1.firstUnaryExp=UnaryExp.getInstance().parseUnaryExp();
        Token token= Global.parser.preReadToken();
        if (token.getType().equals(LexType.MULT)
        || token.getType().equals(LexType.DIV)
        || token.getType().equals(LexType.MOD)){
            mulExp1.operator=Global.parser.getNextToken();
            mulExp1.mulExp=MulExp.getInstance().parseMulExp();
        }
        return mulExp1;
    }
}
