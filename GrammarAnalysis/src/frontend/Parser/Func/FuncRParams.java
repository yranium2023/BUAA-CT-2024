package frontend.Parser.Func;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Exp.Exp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 函数实参表 FuncRParams → Exp { ',' Exp }
 * @date 2024/10/9 21:14
 */
public class FuncRParams {
    private static final String name="<FuncRParams>";
    private static FuncRParams instance=new FuncRParams();
    private Exp firstExp =null;
    private List<Token>commas=new ArrayList<>();
    private List<Exp>exps=new ArrayList<>();
    private FuncRParams(){

    }
    public static FuncRParams getInstance(){
        return instance;
    }

    public FuncRParams parseFuncRParams(){
        FuncRParams funcRParams=new FuncRParams();
        funcRParams.firstExp =Exp.getInstance().parseExp();
        Token token= Global.parser.getNextToken();
        while (token.getType().equals(LexType.COMMA)){
            funcRParams.commas.add(token);
            funcRParams.exps.add(Exp.getInstance().parseExp());
            token= Global.parser.getNextToken();
        }
        Global.parser.unReadPrevToken();
        return funcRParams;
    }
    public void print() throws IOException {
        firstExp.print();
        for(int i=0;i<commas.size();i++){
            commas.get(i).print();
            exps.get(i).print();
        }
        Global.parser.out.write(name+"\n");
    }

}
