package frontend.Parser.Exp;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Lexer;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 基本表达式 PrimaryExp → '(' Exp ')' | LVal | Number | Character
 * 存在 j 类错误
 * @date 2024/10/9 21:14
 */
public class PrimaryExp {
    private static final String name="<PrimaryExp>";
    private Token leftParent=null;
    private Exp exp=null;
    private Token rightParent=null;
    private LVal lVal=null;
    private Number number=null;
    private Character character=null;
    private static PrimaryExp instance=new PrimaryExp();
    private PrimaryExp(){

    }
    public static PrimaryExp getInstance(){
        return instance;
    }
    public PrimaryExp parsePrimaryExp(){
        PrimaryExp primaryExp=new PrimaryExp();
        Token token= Global.parser.getNextToken();
        Global.parser.unReadPrevToken();
        if(token.getType().equals(LexType.LPARENT)){
            // '(' Exp ')'
            primaryExp.leftParent=Global.parser.getNextToken();
            primaryExp.exp=Exp.getInstance().parseExp();
            primaryExp.rightParent= Global.parser.match(LexType.RPARENT);
        }else if(token.getType().equals(LexType.INTCON)){
            // Number
            primaryExp.number=Number.getInstance().parseNumber();
        }else if(token.getType().equals(LexType.CHRCON)){
            // Character
            primaryExp.character=Character.getInstance().parseCharacter();
        }else{
            // LVal
            primaryExp.lVal=LVal.getInstance().parseLVal();
        }
        return primaryExp;
    }
    public void print() throws IOException {
        if(lVal!=null){
            lVal.print();
        }else if(number!=null){
            number.print();
        }else if(character!=null){
            character.print();
        }else{
            leftParent.print();
            exp.print();
            rightParent.print();
        }
        Global.parser.out.write(name+"\n");
    }
}
