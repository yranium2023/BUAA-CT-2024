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
 * 转换成非递归形式为： AddExp → MulExp { ('+' | '-') MulExp }
 * @date 2024/10/9 21:14
 */
public class AddExp {
    private  MulExp firstMulExp=null;
    private List<Token>operators=new ArrayList<>();
    private List<MulExp>mulExps=new ArrayList<>();
    private AddExp(){

    }
    private static AddExp instance=new AddExp();
    public static AddExp getInstance(){
        return instance;
    }
    public AddExp parseAddExp(){
        AddExp addExp=new AddExp();
        addExp.firstMulExp=MulExp.getInstance().parseMulExp();
        Token token=Global.parser.getNextToken();
        while(token.getType().equals(LexType.PLUS)
        || token.getType().equals(LexType.MINU)){
            addExp.operators.add(token);
            addExp.mulExps.add(MulExp.getInstance().parseMulExp());
        }
        Global.parser.unReadPrevToken();
        return addExp;
    }


}
