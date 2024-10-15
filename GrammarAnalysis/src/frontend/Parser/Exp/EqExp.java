package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 相等性表达式 EqExp → RelExp | EqExp ('==' | '!=') RelExp
 * 转换成右递归为 EqExp → RelExp | RelExp ('==' | '!=') EqExp
 * @date 2024/10/9 21:15
 */
public class EqExp {
    private static final String name="<EqExp>";
    private RelExp relExp=null;
    private Token operator=null;
    private EqExp eqExp=null;
    private EqExp(){}
    private static EqExp instance=new EqExp();
    public static EqExp getInstance(){
        return instance;
    }
    public EqExp parseEqExp(){
        EqExp eqExp1=new EqExp();
        eqExp1.relExp=RelExp.getInstance().parseRelExp();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.EQL)
        || token.getType().equals(LexType.NEQ)){
            eqExp1.operator=Global.parser.getNextToken();
            eqExp1.eqExp=EqExp.getInstance().parseEqExp();
        }
        return eqExp1;
    }
    public void print() throws IOException {
        relExp.print();
        Global.parser.out.write(name+"\n");
        if(operator!=null){
            operator.print();
            eqExp.print();
        }
    }
}
