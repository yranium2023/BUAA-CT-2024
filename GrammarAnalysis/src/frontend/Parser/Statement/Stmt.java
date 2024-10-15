package frontend.Parser.Statement;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Decl.BType;
import frontend.Parser.Exp.Cond;
import frontend.Parser.Exp.Exp;
import frontend.Parser.Exp.LVal;
import frontend.Parser.Parser;

import javax.swing.text.AbstractDocument;
import java.io.IOException;
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
    private static final String name="<Stmt>";
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
    private List<Stmt> stmts=new ArrayList<>();
    private Stmt stmt=null;
    private Token elseToken=null;
    private Token forToken=null;
    private ForStmt firstForStmt=null;
    private ForStmt secondForStmt=null;
    private List<Token> semicns=new ArrayList<>();
    private Token breakToken=null;
    private Token continueToken=null;
    private Token returnToken=null;
    private Exp exp=null;
    private LVal lVal=null;
    private Token assignToken=null;
    private Token getIntToken=null;
    private Token getCharToken=null;

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
            stmt.stmts.add(Stmt.getInstance().parseStmt());
            token=Global.parser.preReadToken();
            if(token.getType().equals(LexType.ELSETK)){
                stmt.elseToken=Global.parser.getNextToken();
                stmt.stmts.add(Stmt.getInstance().parseStmt());
            }
            stmt.type=StmtType.If;
        }else if(token.getType().equals(LexType.FORTK)){
            // 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
            stmt.forToken=Global.parser.getNextToken();
            stmt.leftParent=Global.parser.match(LexType.LPARENT);
            token=Global.parser.preReadToken();
            if(token.getType().equals(LexType.IDENFR)){
                stmt.firstForStmt=ForStmt.getInstance().parseForStmt();
            }
            stmt.semicns.add(Global.parser.match(LexType.SEMICN));
            token=Global.parser.preReadToken();
            if(Parser.isExp(token)){
                stmt.cond=Cond.getInstance().parseCond();
            }
            stmt.semicns.add(Global.parser.match(LexType.SEMICN));
            token=Global.parser.preReadToken();
            if(token.getType().equals(LexType.IDENFR)){
                stmt.secondForStmt=ForStmt.getInstance().parseForStmt();
            }
            stmt.rightParent=Global.parser.match(LexType.RPARENT);
            stmt.stmt=Stmt.getInstance().parseStmt();
            stmt.type=StmtType.For;
        }else if(token.getType().equals(LexType.BREAKTK)){
            // 'break' ';'
            stmt.breakToken=Global.parser.getNextToken();
            stmt.semicn=Global.parser.match(LexType.SEMICN);
            stmt.type=StmtType.Break;
        }else if(token.getType().equals(LexType.CONTINUETK)){
            // 'continue' ';'
            stmt.continueToken=Global.parser.getNextToken();
            stmt.semicn=Global.parser.match(LexType.SEMICN);
            stmt.type=StmtType.Continue;
        } else if(token.getType().equals(LexType.RETURNTK)){
            // 'return' [Exp] ';'
            stmt.returnToken=Global.parser.getNextToken();
            token=Global.parser.preReadToken();
            if(Parser.isExp(token)){
                stmt.exp=Exp.getInstance().parseExp();
            }
            stmt.semicn=Global.parser.match(LexType.SEMICN);
            stmt.type=StmtType.Return;
        }else{
            int index=Global.parser.getIndex();
            int assign=Global.parser.getAssignIndex();
            if(assign>index){
                stmt.lVal=LVal.getInstance().parseLVal();
                stmt.assignToken=Global.parser.match(LexType.ASSIGN);
                token=Global.parser.preReadToken();
                if(token.getType().equals(LexType.GETINTTK)){
                    // LVal '=' 'getint''('')'';'
                    stmt.getIntToken=Global.parser.match(LexType.GETINTTK);
                    stmt.leftParent=Global.parser.match(LexType.LPARENT);
                    stmt.rightParent=Global.parser.match(LexType.RPARENT);
                    stmt.semicn=Global.parser.match(LexType.SEMICN);
                    stmt.type=StmtType.LvalAssignGetint;
                }else if(token.getType().equals(LexType.GETCHARTK)){
                    // LVal '=' 'getchar''('')'';'
                    stmt.getCharToken=Global.parser.match(LexType.GETCHARTK);
                    stmt.leftParent=Global.parser.match(LexType.LPARENT);
                    stmt.rightParent=Global.parser.match(LexType.RPARENT);
                    stmt.semicn=Global.parser.match(LexType.SEMICN);
                    stmt.type=StmtType.LvalAssignGetchar;
                }else{
                    // LVal '=' Exp ';'
                    stmt.exp=Exp.getInstance().parseExp();
                    stmt.semicn=Global.parser.match(LexType.SEMICN);
                    stmt.type=StmtType.LValAssignExp;
                }
            }else{
                //[Exp] ';'
                token=Global.parser.preReadToken();
                if(Parser.isExp(token)){
                    stmt.exp=Exp.getInstance().parseExp();
                }
                stmt.semicn=Global.parser.match(LexType.SEMICN);
                stmt.type=StmtType.Exp;
            }
        }
        return  stmt;
    }
    public void print() throws IOException {
        switch (type){
            case LValAssignExp :
                //LVal '=' Exp ';'
                lVal.print();
                assignToken.print();
                exp.print();
                semicn.print();
                break;
            case Exp:
                //[Exp] ';'
                if(exp!=null){
                    exp.print();
                }
                semicn.print();
                break;
            case Block:
                //Block
                block.print();
                break;
            case If:
                //'if' '(' Cond ')' Stmt [ 'else' Stmt ]
                ifToken.print();
                leftParent.print();
                cond.print();
                rightParent.print();
                stmts.get(0).print();
                if(elseToken!=null){
                    elseToken.print();
                    stmts.get(1).print();
                }
                break;
            case For:
                // 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
                forToken.print();
                leftParent.print();
                if(firstForStmt!=null){
                    firstForStmt.print();
                }
                semicns.get(0).print();
                if(cond!=null){
                    cond.print();
                }
                semicns.get(1).print();
                if(secondForStmt!=null){
                    secondForStmt.print();
                }
                rightParent.print();
                stmt.print();
                break;
            case Break:
                // 'break' ';'
                breakToken.print();
                semicn.print();
                break;
            case Continue:
                //'continue' ';'
                continueToken.print();
                semicn.print();
                break;
            case Return:
                // 'return' [Exp] ';'
                returnToken.print();
                if(exp!=null){
                    exp.print();
                }
                semicn.print();
                break;
            case LvalAssignGetint:
                // LVal '=' 'getint''('')'';'
                lVal.print();
                assignToken.print();
                getIntToken.print();
                leftParent.print();
                rightParent.print();
                semicn.print();
                break;
            case LvalAssignGetchar:
                // LVal '=' 'getchar''('')'';'
                lVal.print();
                assignToken.print();
                getCharToken.print();
                leftParent.print();
                rightParent.print();
                semicn.print();
                break;
            case Printf:
                //'printf' '(' StringConst { ',' Exp } ')' ';'
                printfToken.print();
                leftParent.print();
                strConst.print();
                for(int i=0;i<commas.size();i++){
                    commas.get(i).print();
                    exps.get(i).print();
                }
                rightParent.print();
                semicn.print();
                break;
        }
        Global.parser.out.write(name+"\n");
    }

}
