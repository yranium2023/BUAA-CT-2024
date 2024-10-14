package frontend.Parser;

import frontend.Error.Error;
import frontend.Error.ErrorHandler;
import frontend.Error.ErrorType;
import frontend.Lexer.LexType;
import frontend.Lexer.Lexer;
import frontend.Lexer.Token;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * @date 2024/10/8 19:42
 */
public class Parser {
    private Lexer lexer;
    private BufferedWriter out;
    private int index;
    private List<Token> tokenList;

    public Parser(Lexer lexer, BufferedWriter out) {
        this.lexer = lexer;
        this.out = out;
        tokenList=lexer.tokenList;
        index = -1;
    }
    public Token getNextToken(){
        if(hasNextToken()){
            return tokenList.get(++index);
        }
        System.out.println("Token reach the limit");
        return null;
    }
    public Token preReadToken(){
        if(hasNextToken()){
            return tokenList.get(index+1);
        }
        System.out.println("Token reach the limit");
        return null;
    }
    public Token getCurToken(){
        if(index>=0&&index<tokenList.size()){
            return tokenList.get(index);
        }
        return null;
    }
    public void unReadToken(int unReadNum){
        index-=unReadNum;
    }
    public void unReadPrevToken(){
        unReadToken(1);
    }
    public boolean hasNextToken(){
        if(tokenList.size()>index+1){
            return true;
        }
        return false;
    }
    public Token match(LexType lexType){
        Token now=tokenList.get(index);
        Token next=null;
        if(tokenList.size()>index+1){
            next=tokenList.get(index+1);
        }else{
            System.out.println("Token reach the limit");
        }
        if(next.getType().equals(lexType)){
            ++index;
            return next;
        }else if(lexType.equals(LexType.SEMICN)){
            ErrorHandler.getInstance().addError(new Error(ErrorType.MISSING_SEMICN, now.getLineNum()));
            return new Token(now.getLineNum(),LexType.SEMICN);
        }else if(lexType.equals(LexType.RPARENT)){
            ErrorHandler.getInstance().addError(new Error(ErrorType.MISSING_R_PARENT, now.getLineNum()));
            return new Token(now.getLineNum(),LexType.RPARENT);
        }else if(lexType.equals(LexType.RBRACK)){
            ErrorHandler.getInstance().addError(new Error(ErrorType.MISSING_R_BACKET, now.getLineNum()));
            return new Token(now.getLineNum(),LexType.RBRACK);
        }else{
            System.out.println("EXPECT "+lexType+" at line "+now.getLineNum());
            return null;
        }
    }
    public static boolean isExp(Token token) {
        return token.getType().equals(LexType.IDENFR)
                || token.getType().equals(LexType.PLUS)
                || token.getType().equals(LexType.MINU)
                || token.getType().equals(LexType.NOT)
                || token.getType().equals(LexType.LPARENT)
                || token.getType().equals(LexType.INTCON)
                || token.getType().equals(LexType.CHRCON);
    }
    public void toParser(){
        CompUnit compUnit=new CompUnit();
    }
}
