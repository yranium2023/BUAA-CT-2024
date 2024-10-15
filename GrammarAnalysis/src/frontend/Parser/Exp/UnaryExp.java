package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Func.FuncRParams;
import frontend.Parser.Parser;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 一元表达式 UnaryExp → PrimaryExp | Ident '(' [FuncRParams] ')' | UnaryOp UnaryExp
 * 存在 j 类错误
 * @date 2024/10/9 21:14
 */
public class UnaryExp {
    private static final String name="<PrimaryExp>";
    private PrimaryExp primaryExp = null;
    private Token ident = null;
    private Token leftParent = null;
    private FuncRParams funcRParams = null;
    private Token rightParent =null;
    private UnaryOp unaryOp=null;
    private UnaryExp unaryExp=null;
    private static UnaryExp instance=new UnaryExp();
    private UnaryExp(){

    }
    public static UnaryExp getInstance(){
        return instance;
    }
    //判断是否为  primaryExp
    private boolean isPrimaryExpFirst(Token first){
        return first.getType().equals(LexType.LPARENT)
                || first.getType().equals(LexType.IDENFR)
                || first.getType().equals(LexType.INTCON)
                || first.getType().equals(LexType.CHRCON);
    }
    //判断是否为  Ident '(' [FuncRParams] ')'
    private boolean isIdentFirst(Token first,Token second){
        return first.getType().equals(LexType.IDENFR)
                && second.getType().equals(LexType.LPARENT);
    }
    //判断是否为  UnaryOp UnaryExp
    private boolean isUnaryOpFirst(Token first){
        return first.getType().equals(LexType.PLUS)
                || first.getType().equals(LexType.MINU)
                || first.getType().equals(LexType.NOT);
    }

    public UnaryExp parseUnaryExp(){
        UnaryExp unaryExp1=new UnaryExp();
        //预读2个Token
        Token first= Global.parser.getNextToken();
        Token second= Global.parser.getNextToken();
        Global.parser.unReadToken(2);
        if(isIdentFirst(first,second)){
            unaryExp1.ident =Global.parser.getNextToken();
            unaryExp1.leftParent =Global.parser.getNextToken();
            Token token=Global.parser.getNextToken();
            Global.parser.unReadPrevToken();
            if(Parser.isExp(token)){
                unaryExp1.funcRParams= FuncRParams.getInstance().parseFuncRParams();
            }
            unaryExp1.rightParent=Global.parser.match(LexType.RPARENT);
        }else if(isPrimaryExpFirst(first)){
            unaryExp1.primaryExp=PrimaryExp.getInstance().parsePrimaryExp();
        }else if(isUnaryOpFirst(first)){
            unaryExp1.unaryOp=UnaryOp.getInstance().parseUnaryOp();
            unaryExp1.unaryExp=UnaryExp.getInstance().parseUnaryExp();
        }else{
            System.out.println("ERROR: NOT UNARYEXP");
        }
        return unaryExp1;
    }
    public void print() throws IOException {
        if(primaryExp!=null){
            primaryExp.print();
        }else if(ident!=null){
            ident.print();
            leftParent.print();
            if(funcRParams!=null){
                funcRParams.print();
            }
            rightParent.print();
        }else{
            unaryOp.print();
            unaryExp.print();
        }
        Global.parser.out.write(name+"\n");
    }



}

