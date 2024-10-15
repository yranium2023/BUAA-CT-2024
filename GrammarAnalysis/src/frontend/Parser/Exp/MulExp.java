package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 乘除模表达式 MulExp → UnaryExp | MulExp ('*' | '/' | '%') UnaryExp
 * 转化为右递归的形式为：MulExp → UnaryExp ｜ UnaryExp ('*' | '/' | '%') MulExp
 * @date 2024/10/9 21:14
 */
public class MulExp {
    private static final String name="<MulExp>";
    private static MulExp instance=new MulExp();
    private UnaryExp unaryExp =null;
    private Token operator=null;
    private MulExp mulExp=null;
    private MulExp(){

    }
    public static MulExp getInstance(){
        return instance;
    }
    public MulExp parseMulExp(){
        MulExp mulExp1=new MulExp();
        mulExp1.unaryExp =UnaryExp.getInstance().parseUnaryExp();
        Token token= Global.parser.preReadToken();
        if (token.getType().equals(LexType.MULT)
        || token.getType().equals(LexType.DIV)
        || token.getType().equals(LexType.MOD)){
            mulExp1.operator=Global.parser.getNextToken();
            mulExp1.mulExp=MulExp.getInstance().parseMulExp();
        }
        return mulExp1;
    }
    public void print() throws IOException {
        unaryExp.print();
        Global.parser.out.write(name+"\n");
        if(operator!=null){
            operator.print();
            mulExp.print();
        }
    }
}
