package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Exp.Exp;
import frontend.Parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 变量初值 InitVal → Exp | '{' [ Exp { ',' Exp } ] '}' | StringConst
 * @date 2024/10/9 21:11
 */
public class InitVal {
    private static final String name="<InitVal>";
    private Exp exp=null;
    private Token leftBrace=null;
    private Exp firstExp=null;
    private List<Token> commas=new ArrayList<>();
    private List<Exp> exps=new ArrayList<>();
    private Token rightBrace=null;
    private Token strConst=null;
    private InitVal(){

    }
    private static InitVal instance=new InitVal();
    public static InitVal getInstance(){
        return instance;
    }
    public InitVal parseInitVal(){
        InitVal initVal=new InitVal();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.STRCON)){
            initVal.strConst=Global.parser.getNextToken();
        }else if(token.getType().equals(LexType.LBRACE)){
            initVal.leftBrace=Global.parser.getNextToken();
            token=Global.parser.preReadToken();
            if(Parser.isExp(token)){
                initVal.firstExp=Exp.getInstance().parseExp();
                token=Global.parser.preReadToken();
                while(token.getType().equals(LexType.COMMA)){
                    initVal.commas.add(Global.parser.getNextToken());
                    initVal.exps.add(Exp.getInstance().parseExp());
                    token=Global.parser.preReadToken();
                }
            }
            initVal.rightBrace=Global.parser.match(LexType.RBRACE);
        }else{
            initVal.exp=Exp.getInstance().parseExp();
        }
        return initVal;
    }
    public void print() throws IOException {
        if(exp!=null){
            exp.print();
        }else if(strConst!=null){
            strConst.print();
        }else{
            leftBrace.print();
            if(firstExp!=null){
                firstExp.print();
                for(int i=0;i<commas.size();i++){
                    commas.get(i).print();
                    exps.get(i).print();
                }
            }
            rightBrace.print();
        }
        Global.parser.out.write(name+"\n");
    }
}
