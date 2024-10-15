package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 逻辑与表达式 LAndExp → EqExp | LAndExp '&&' EqExp
 * 转换成右递归的形式为：LAndExp → EqExp | EqExp '&&' LAndExp
 * @date 2024/10/9 21:15
 */
public class LAndExp {
    private static final String name="<LAndExp>";
    private EqExp eqExp=null;
    private Token operator =null;
    private LAndExp lAndExp=null;
    private LAndExp(){}
    private static LAndExp instance=new LAndExp();
    public static LAndExp getInstance(){
        return instance;
    }
    public LAndExp parseLAndExp(){
        LAndExp lAndExp1=new LAndExp();
        lAndExp1.eqExp=EqExp.getInstance().parseEqExp();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.AND)){
            lAndExp1.operator=Global.parser.getNextToken();
            lAndExp1.lAndExp=LAndExp.getInstance().parseLAndExp();
        }
        return lAndExp1;
    }
    public void print() throws IOException {
        eqExp.print();
        Global.parser.out.write(name+"\n");
        if(operator!=null){
            operator.print();
            lAndExp.print();
        }
    }
}
