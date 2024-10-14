package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Exp.ConstExp;

/**
 * @author 吴鹄远
 * @Description
 * 变量定义 VarDef → Ident [ '[' ConstExp ']' ] | Ident [ '[' ConstExp ']' ] '=' InitVal
 * 即 VarDef → Ident [ '[' ConstExp ']' ] [ '=' InitVal ]
 * 存在 k 类错误
 * @date 2024/10/9 21:11
 */
public class VarDef {
    private Token ident=null;
    private Token leftBracket=null;
    private ConstExp constExp=null;
    private Token rightBracket=null;
    private Token assign=null;
    private InitVal initVal=null;
    private VarDef(){

    }
    private static VarDef instance=new VarDef();
    public static VarDef getInstance(){
        return instance;
    }

    public VarDef parseVarDef(){
        VarDef varDef=new VarDef();
        varDef.ident= Global.parser.match(LexType.IDENFR);
        Token token=Global.parser.preReadToken();
        if(token.getType().equals(LexType.LBRACK)){
            varDef.leftBracket=Global.parser.getNextToken();
            varDef.constExp=ConstExp.getInstance().parseConstExp();
            varDef.rightBracket=Global.parser.match(LexType.RBRACK);
            token=Global.parser.preReadToken();
        }
        if(token.getType().equals(LexType.ASSIGN)){
            varDef.assign=Global.parser.getNextToken();
            varDef.initVal=InitVal.getInstance().parseInitVal();
        }
        return varDef;
    }
}
