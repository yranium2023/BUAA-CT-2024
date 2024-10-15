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
    private Token token;
    private static BType instance=new BType(null);

    public static BType getInstance() {
        return instance;
    }

    private BType(Token token) {
        this.token = token;
    }
    public BType parseBtype()  {
        Token bType= Global.parser.getNextToken();
        if(bType.getType().equals(LexType.INTTK)
                ||bType.getType().equals(LexType.CHARTK)){
            return new BType(bType);
        }else{
            Global.parser.unReadPrevToken();
            System.out.println("ERROR: EXPECT INTTK OR CHARTK");
            return null;
        }
    }
    public void print() throws IOException{
        token.print();
    }
}
