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
 * 转化为非递归的形式为：MulExp → UnaryExp { ('*' | '/' | '%') UnaryExp }
 * @date 2024/10/9 21:14
 */
public class MulExp {
    private static MulExp instance=new MulExp();
    private UnaryExp firstUnaryExp=null;
    private List<Token>operators=new ArrayList<>();
    private List<UnaryExp>unaryExps=new ArrayList<>();
    private MulExp(){

    }
    public static MulExp getInstance(){
        return instance;
    }
    public MulExp parseMulExp(){
        MulExp mulExp=new MulExp();
        mulExp.firstUnaryExp=UnaryExp.getInstance().parseUnaryExp();
        Token token= Global.parser.getNextToken();
        while(token.getType().equals(LexType.MULT)
        || token.getType().equals(LexType.DIV)
        || token.getType().equals(LexType.MOD)){
            mulExp.operators.add(token);
            mulExp.unaryExps.add(UnaryExp.getInstance().parseUnaryExp());
            token= Global.parser.getNextToken();
        }
        Global.parser.unReadPrevToken();
        return mulExp;
    }
}
