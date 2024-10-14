package frontend.Parser.Statement;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Decl.BType;
import frontend.Parser.Exp.Cond;
import frontend.Parser.Exp.Exp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 语句 Stmt → LVal '=' Exp ';'   //i
 * | [Exp] ';'  //i
 * | Block
 * | 'if' '(' Cond ')' Stmt [ 'else' Stmt ] //j
 * | 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
 * | 'break' ';'    //i
 * | 'continue' ';'   //i
 * | 'return' [Exp] ';'     //i
 * | LVal '=' 'getint''('')'';'     //ij
 * | LVal '=' 'getchar''('')'';'    //ij
 * | 'printf' '(' StringConst { ',' Exp } ')' ';'  //ij
 * @date 2024/10/9 21:13
 */
public class Stmt {
    private Stmt(){}
    private static Stmt instance=new Stmt();
    public static Stmt getInstance(){
        return instance;
    }
    private StmtType type=null;
    private Block block=null;
    private Token printfToken=null;
    private Token leftParent=null;
    private Token strConst=null;
    private List<Token> commas=new ArrayList<>();
    private List<Exp> exps=new ArrayList<>();
    private Token rightParent=null;
    private Token semicn=null;
    private Token ifToken=null;
    private Cond cond=null;
    public Stmt parseStmt(){
        Stmt stmt=new Stmt();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.LBRACE)){
            //Block
            stmt.block=Block.getInstance().parseBlock();
            stmt.type=StmtType.Block;
        }else if(token.getType().equals(LexType.PRINTFTK)){
            //'printf' '(' StringConst { ',' Exp } ')' ';'
            stmt.printfToken=Global.parser.getNextToken();
            stmt.leftParent=Global.parser.match(LexType.LPARENT);
            stmt.strConst=Global.parser.match(LexType.STRCON);
            while(Global.parser.preReadToken().getType().equals(LexType.COMMA)){
                stmt.commas.add(Global.parser.getNextToken());
                stmt.exps.add(Exp.getInstance().parseExp());
            }
            stmt.rightParent=Global.parser.match(LexType.RPARENT);
            stmt.semicn=Global.parser.match(LexType.SEMICN);
            stmt.type=StmtType.Printf;
        }else if(token.getType().equals(LexType.IFTK)){
            //'if' '(' Cond ')' Stmt [ 'else' Stmt ] //j
            stmt.ifToken=Global.parser.getNextToken();
            stmt.leftParent=Global.parser.match(LexType.LPARENT);
            stmt.cond=Cond.getInstance().parseCond();
            stmt.rightParent=Global.parser.match(LexType.RPARENT);

        }
    }

}
