package frontend.Parser.Func;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

/**
 * @author 吴鹄远
 * @Description
 * 函数类型 FuncType → 'void' | 'int' | 'char'
 * @date 2024/10/9 21:12
 */
public class FuncType {
    private Token type=null;
    private FuncType(){

    }
    private static FuncType instance=new FuncType();
    public static FuncType getInstance(){
        return instance;
    }
    public FuncType parseFuncType(){
        FuncType funcType=new FuncType();
        Token token= Global.parser.getNextToken();
        if(token.getType().equals(LexType.VOIDTK)
        || token.getType().equals(LexType.INTTK)
        || token.getType().equals(LexType.CHARTK)){
            funcType.type=token;
        }else{
            System.out.println("Error: EXPECT FUNCTYPE");
        }
        return funcType;
    }

}
