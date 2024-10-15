package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Parser;
import frontend.Parser.Statement.ForStmt;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 声明 Decl → ConstDecl | VarDecl
 * @date 2024/10/9 21:10
 */
public class Decl {
    private static final String name="<Decl";
    private ConstDecl constDecl;
    private VarDecl varDecl;
    private Decl(){

    }
    private static Decl instance=new Decl();
    public static Decl getInstance(){
        return instance;
    }

    public Decl parseDecl(){
        Decl decl=new Decl();
        Token token=Global.parser.preReadToken();
        if(token.getType().equals(LexType.CONSTTK)){
            decl.constDecl=ConstDecl.getInstance().parseConstDecl();
        }else{
            decl.varDecl=VarDecl.getInstance().parseVarDecl();
        }
        return decl;
    }
    public void print() throws IOException {
        if(constDecl!=null){
            constDecl.print();
        }else{
            varDecl.print();
        }
    }

}
