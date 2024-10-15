package frontend.Parser.Func;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 函数形参表 FuncFParams → FuncFParam { ',' FuncFParam }
 * @date 2024/10/9 21:12
 */
public class FuncFParams {
    private static final String name="<FuncFParams>";
    private FuncFParam firstFuncFParam=null;
    private List<Token> commas=new ArrayList<>();
    private List<FuncFParam> funcFParams=new ArrayList<>();
    private FuncFParams(){

    }
    private static FuncFParams instance=new FuncFParams();
    public static FuncFParams getInstance(){
        return instance;
    }
    public FuncFParams parseFuncFParams(){
        FuncFParams funcFParams1=new FuncFParams();
        funcFParams1.firstFuncFParam=FuncFParam.getInstance().parseFuncFParam();
        while(Global.parser.preReadToken().getType().equals(LexType.COMMA)){
            funcFParams1.commas.add(Global.parser.getNextToken());
            funcFParams1.funcFParams.add(FuncFParam.getInstance().parseFuncFParam());
        }
        return funcFParams1;
    }
    public void print() throws IOException {
        firstFuncFParam.print();
        for(int i=0;i<commas.size();i++){
            commas.get(i).print();
            funcFParams.get(i).print();
        }
        Global.parser.out.write(name+"\n");
    }
}
