package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Exp.ConstExp;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 常量定义 ConstDef → Ident [ '[' ConstExp ']' ] '=' ConstInitVal
 * 存在 k 类错误
 * @date 2024/10/9 21:11
 */
public class ConstDef {
    private static final String name="<ConstDef>";
    private Token ident =null; //Ident
    private Token leftBracket=null; //'['
    private ConstExp constExp=null;
    private Token rightBracket=null; //']'
    private Token assignToken =null; //'='
    private ConstInitVal constInitVal=null;
    private ConstDef() {
    }
    private static ConstDef instance=new ConstDef();

    public static ConstDef getInstance(){
        return instance;
    }

    public ConstDef parseConstDef(){
        ConstDef constDef=new ConstDef();//空白的ConstDef
        constDef.ident =Global.parser.match(LexType.IDENFR);
        Token token=Global.parser.preReadToken();
        if(token.getType().equals(LexType.LBRACK)){
            //一维数组
            constDef.leftBracket=Global.parser.getNextToken();
            constDef.constExp=ConstExp.getInstance().parseConstExp();
            constDef.rightBracket=Global.parser.match(LexType.RBRACK);
        }
        //常表达式
        constDef.assignToken =Global.parser.match(LexType.ASSIGN);
        constDef.constInitVal=ConstInitVal.getInstance().parseConstInitVal();
        return constDef;
    }
    public void print() throws IOException{
        ident.print();
        if(leftBracket!=null){
            leftBracket.print();
            constExp.print();
            rightBracket.print();
        }
        assignToken.print();
        constInitVal.print();
        Global.parser.out.write(name+"\n");
    }
}
