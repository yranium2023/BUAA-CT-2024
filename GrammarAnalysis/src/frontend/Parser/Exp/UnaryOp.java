package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 单目运算符 UnaryOp → '+' | '−' | '!'
 * @date 2024/10/9 21:14
 */
public class UnaryOp {
    private static final String name="<UnaryOp>";
    private Token op=null;
    private static UnaryOp instance=new UnaryOp();
    private UnaryOp(){

    }
    public static UnaryOp getInstance(){
        return instance;
    }
    public UnaryOp parseUnaryOp(){
        UnaryOp unaryOp=new UnaryOp();
        Token token= Global.parser.getNextToken();
        if(!(token.getType().equals(LexType.PLUS) ||
                token.getType().equals(LexType.MINU) ||
                token.getType().equals(LexType.NOT))){
            System.out.println("EXPECT UNARYOP HERE");
        }
        unaryOp.op=token;
        return unaryOp;
    }
    public void print() throws IOException {
        op.print();
        Global.parser.out.write(name+"\n");
    }
}
