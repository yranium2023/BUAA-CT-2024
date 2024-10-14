package frontend.Parser.Func;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Statement.Block;



/**
 * @author 吴鹄远
 * @Description
 * 函数定义 FuncDef → FuncType Ident '(' [FuncFParams] ')' Block
 * 存在 j 类错误
 * @date 2024/10/9 21:12
 */
public class FuncDef {
    private FuncType funcType=null;
    private Token ident=null;
    private Token leftParent=null;
    private FuncFParams funcFParams=null;
    private Token rightParent=null;
    private Block block=null;
    private FuncDef(){

    }
    private static FuncDef instance=new FuncDef();
    public static FuncDef getInstance(){
        return instance;
    }
    private FuncDef parseFuncDef(){
        FuncDef funcDef=new FuncDef();
        funcDef.funcType=FuncType.getInstance().parseFuncType();
        funcDef.ident= Global.parser.match(LexType.IDENFR);
        funcDef.leftParent=Global.parser.match(LexType.LPARENT);
        Token token=Global.parser.preReadToken();
        if(token.getType().equals(LexType.INTTK)
                || token.getType().equals(LexType.CHARTK)){
            funcDef.funcFParams=FuncFParams.getInstance().parseFuncFParams();
        }
        funcDef.rightParent=Global.parser.match(LexType.RPARENT);
        funcDef.block=Block.getInstance().parseBlock();
        return funcDef;
    }

}
