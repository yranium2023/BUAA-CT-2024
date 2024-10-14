package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 加减表达式 AddExp → MulExp | AddExp ('+' | '−') MulExp
 * 转换成右递归形式为： AddExp → MulExp | MulExp ('+' | '-') AddExp
 * @date 2024/10/9 21:14
 */
public class AddExp {
    private  MulExp firstMulExp=null;
    private Token operator=null;
    private AddExp addExp=null;
    private AddExp(){

    }
    private static AddExp instance=new AddExp();
    public static AddExp getInstance(){
        return instance;
    }
    public AddExp parseAddExp(){
        AddExp addExp1=new AddExp();
        addExp1.firstMulExp=MulExp.getInstance().parseMulExp();
        Token token=Global.parser.preReadToken();
        if(token.getType().equals(LexType.PLUS)
        || token.getType().equals(LexType.MINU)){
            addExp1.operator=Global.parser.getNextToken();
            addExp1.addExp=AddExp.getInstance().parseAddExp();
        }
        return addExp1;
    }


}
