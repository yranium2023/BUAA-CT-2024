package frontend.Parser.Statement;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Exp.Exp;
import frontend.Parser.Exp.LVal;

/**
 * @author 吴鹄远
 * @Description
 * 语句 ForStmt → LVal '=' Exp
 * @date 2024/10/9 21:13
 */
public class ForStmt {
    private LVal lVal=null;
    private Token assign=null;
    private Exp exp=null;
    private ForStmt(){}
    private static ForStmt instance=new ForStmt();
    public static ForStmt getInstance(){
        return instance;
    }
    public ForStmt parseForStmt(){
        ForStmt forStmt=new ForStmt();
        forStmt.lVal=LVal.getInstance().parseLVal();
        forStmt.assign= Global.parser.match(LexType.ASSIGN);
        forStmt.exp=Exp.getInstance().parseExp();
        return forStmt;
    }
}
