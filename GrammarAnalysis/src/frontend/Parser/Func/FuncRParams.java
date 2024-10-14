package frontend.Parser.Func;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Lexer;
import frontend.Lexer.Token;
import frontend.Parser.Exp.Exp;
import frontend.Parser.Exp.LVal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 函数实参表 FuncRParams → Exp { ',' Exp }
 * @date 2024/10/9 21:14
 */
public class FuncRParams {
    private static FuncRParams instance=new FuncRParams();
    private Exp expFirst=null;
    private List<Token>commas=new ArrayList<>();
    private List<Exp>exps=new ArrayList<>();
    private FuncRParams(){

    }
    public static FuncRParams getInstance(){
        return instance;
    }

    public FuncRParams parseFuncRParams(){
        FuncRParams funcRParams=new FuncRParams();
        funcRParams.expFirst=Exp.getInstance().parseExp();
        Token token= Global.parser.getNextToken();
        while (token.getType().equals(LexType.COMMA)){
            funcRParams.commas.add(token);
            funcRParams.exps.add(Exp.getInstance().parseExp());
            token= Global.parser.getNextToken();
        }
        Global.parser.unReadPrevToken();
        return funcRParams;
    }

}
