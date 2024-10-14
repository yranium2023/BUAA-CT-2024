package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 常量声明 ConstDecl → 'const' BType ConstDef { ',' ConstDef } ';'
 * 存在 i 类错误
 * @date 2024/10/9 21:10
 */
public class ConstDecl {
    private Token constTk=null; //"const"
    private BType bType=null; //BType
    private ConstDef firstConstDef=null;
    private List<Token>commas=new ArrayList<>(); //','
    private List<ConstDef>constDefs=new ArrayList<>();
    private Token semicn=null; //';'
    private ConstDecl(){

    }
    private static ConstDecl instance=new ConstDecl();
    public static ConstDecl getInstance(){
        return instance;
    }


    public ConstDecl parseConstDecl() {
        ConstDecl constDecl=new ConstDecl();
        constDecl.constTk=Global.parser.match(LexType.CONSTTK);
        constDecl.bType=BType.getInstance().parseBtype();
        constDecl.firstConstDef=ConstDef.getInstance().parseConstDef();
        Token token=Global.parser.preReadToken();
        while(token.getType().equals(LexType.COMMA)){
            constDecl.commas.add(Global.parser.getNextToken());
            constDecl.constDefs.add(ConstDef.getInstance().parseConstDef());
            token=Global.parser.preReadToken();
        }
        constDecl.semicn=Global.parser.match(LexType.COMMA);
        return constDecl;
    }

}
