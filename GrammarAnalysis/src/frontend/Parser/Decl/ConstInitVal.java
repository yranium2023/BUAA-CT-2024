package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Exp.ConstExp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 常量初值 ConstInitVal → ConstExp | '{' [ ConstExp { ',' ConstExp } ] '}' | StringConst
 * @date 2024/10/9 21:11
 */
public class ConstInitVal {
    private ConstExp constExp=null;
    private Token leftBrace=null; //'{'
    private ConstExp firstConstExp=null;
    private List<Token> commas=new ArrayList<>();
    private List<ConstExp> constExps=new ArrayList<>();
    private Token rightBrace=null;
    private Token strConst=null;
    private ConstInitVal(){

    }
    private static ConstInitVal instance=new ConstInitVal();
    public static ConstInitVal getInstance(){
        return instance;
    }
    public ConstInitVal parseConstInitVal(){
        ConstInitVal constInitVal=new ConstInitVal();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.STRCON)){
            constInitVal.strConst=Global.parser.getNextToken();
        }else if(token.getType().equals(LexType.LBRACE)){
            constInitVal.leftBrace=Global.parser.getNextToken();
            token=Global.parser.preReadToken();
            if(!token.getType().equals(LexType.RBRACE)){
                constInitVal.firstConstExp=ConstExp.getInstance().parseConstExp();
                token=Global.parser.preReadToken();
                while(!token.getType().equals(LexType.RBRACE)){
                    constInitVal.commas.add(Global.parser.match(LexType.COMMA));
                    constInitVal.constExps.add(ConstExp.getInstance().parseConstExp());
                    token=Global.parser.preReadToken();
                }
            }
            constInitVal.rightBrace=Global.parser.getNextToken();
        }else{
            constInitVal.constExp=ConstExp.getInstance().parseConstExp();
        }
        return constInitVal;
    }
}
