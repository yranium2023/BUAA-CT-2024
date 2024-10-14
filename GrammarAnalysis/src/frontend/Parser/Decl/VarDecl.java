package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 变量声明 VarDecl → BType VarDef { ',' VarDef } ';'
 * 存在 i 类错误
 * @date 2024/10/9 21:11
 */
public class VarDecl {
    private BType bType;
    private VarDef firstVarDef;
    private List<Token> commas=new ArrayList<>();
    private List<VarDef> varDefs=new ArrayList<>();
    private Token semicn=null;
    private VarDecl(){

    }
    private static VarDecl instance=new VarDecl();
    public static VarDecl getInstance(){
        return instance;
    }
    public VarDecl parseVarDecl(){
        VarDecl varDecl=new VarDecl();
        varDecl.bType= BType.getInstance().parseBtype();
        varDecl.firstVarDef=VarDef.getInstance().parseVarDef();
        Token token=Global.parser.preReadToken();
        while(token.getType().equals(LexType.COMMA)){
            varDecl.commas.add(Global.parser.getNextToken());
            varDecl.varDefs.add(VarDef.getInstance().parseVarDef());
        }
        varDecl.semicn=Global.parser.match(LexType.SEMICN);
        return varDecl;
    }
}
