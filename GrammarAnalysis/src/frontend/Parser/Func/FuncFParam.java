package frontend.Parser.Func;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Decl.BType;
import frontend.Parser.Statement.Block;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 函数形参 FuncFParam → BType Ident ['[' ']']
 * 存在 k 类错误
 * @date 2024/10/9 21:12
 */
public class FuncFParam {
    private static final String name="<FuncFParam>";
    private BType bType=null;
    private Token ident=null;
    private Token leftBracket=null;
    private Token rightBracket=null;
    private FuncFParam(){

    }
    private static FuncFParam instance=new FuncFParam();
    public static FuncFParam getInstance(){
        return instance;
    }
    public FuncFParam parseFuncFParam(){
        FuncFParam funcFParam=new FuncFParam();
        funcFParam.bType=BType.getInstance().parseBtype();
        funcFParam.ident= Global.parser.match(LexType.IDENFR);
        if(Global.parser.preReadToken().getType().equals(LexType.LBRACK)){
            funcFParam.leftBracket=Global.parser.getNextToken();
            funcFParam.rightBracket=Global.parser.match(LexType.RBRACK);
        }
        return funcFParam;
    }
    public void print() throws IOException {
        bType.print();
        ident.print();
        if(leftBracket!=null){
            leftBracket.print();
            rightBracket.print();
        }
        Global.parser.out.write(name+"\n");
    }
}
