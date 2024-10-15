package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description 逻辑或表达式 LOrExp → LAndExp | LOrExp '||' LAndExp
 * 修改成右递归形式为 LOrExp → LAndExp | LAndExp '||' LOrExp
 * @date 2024/10/9 21:15
 */
public class LOrExp {
    private static final String name="<LOrExp>";
    private LAndExp lAndExp=null;
    private Token operator =null;
    private LOrExp lOrExp=null;
    private LOrExp(){}
    private static LOrExp instance=new LOrExp();
    public static LOrExp getInstance(){
        return instance;
    }
    public LOrExp parseLOrExp(){
        LOrExp lOrExp1=new LOrExp();
        lOrExp1.lAndExp=LAndExp.getInstance().parseLAndExp();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.OR)){
            lOrExp1.operator=Global.parser.getNextToken();
            lOrExp1.lOrExp=LOrExp.getInstance().parseLOrExp();
        }
        return lOrExp1;
    }
    public void print() throws IOException {
        lAndExp.print();
        Global.parser.out.write(name+"\n");
        if(operator!=null){
            operator.print();
            lOrExp.print();
        }
    }
}
