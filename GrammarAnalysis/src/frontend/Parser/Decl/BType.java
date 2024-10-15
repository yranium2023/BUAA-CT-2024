package frontend.Parser.Decl;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 基本类型 BType → 'int' | 'char'
 * @date 2024/10/9 21:10
 */
public class BType {
    private static final String name = "<BType>";
    private Token token=null;
    private static BType instance=new BType();

    public static BType getInstance() {
        return instance;
    }

    private BType() {
    }
    public BType parseBtype()  {
        BType bType=new BType();
        Token token1= Global.parser.getNextToken();
        if(token1.getType().equals(LexType.INTTK)
                ||token1.getType().equals(LexType.CHARTK)){
            bType.token=token1;
        }else{
            Global.parser.unReadPrevToken();
            System.out.println("ERROR: EXPECT INTTK OR CHARTK");
        }
        return bType;
    }
    public void print() throws IOException{
        token.print();
    }
}
