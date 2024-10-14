package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

/**
 * @author 吴鹄远
 * @Description
 * 关系表达式 RelExp → AddExp | RelExp ('<' | '>' | '<=' | '>=') AddExp
 * 转换成右递归为 RelExp → AddExp | AddExp ('<' | '>' | '<=' | '>=') RelExp
 * @date 2024/10/9 21:15
 */
public class RelExp {
    private AddExp addExp=null;
    private Token operator=null;
    private RelExp relExp=null;
    private RelExp(){}
    private static RelExp instance=new RelExp();
    public static RelExp getInstance(){
        return instance;
    }
    public RelExp parseRelExp(){
        RelExp relExp1=new RelExp();
        relExp1.addExp=AddExp.getInstance().parseAddExp();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.LSS)
        || token.getType().equals(LexType.GRE)
        || token.getType().equals(LexType.LEQ)
        || token.getType().equals(LexType.GEQ)){
            relExp1.operator=Global.parser.getNextToken();
            relExp1.relExp=RelExp.getInstance().parseRelExp();
        }
        return relExp1;
    }
}
