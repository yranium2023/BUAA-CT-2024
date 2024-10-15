package frontend.Parser.Func;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Statement.Block;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 主函数定义 MainFuncDef → 'int' 'main' '(' ')' Block
 * 存在 j 类错误
 * @date 2024/10/9 21:12
 */
public class MainFuncDef {
    private static final String name="<MainFuncDef>";
    private Token intToken=null;
    private Token mainToken=null;
    private Token leftParent=null;
    private Token rightParent=null;
    private Block block=null;
    private MainFuncDef(){}
    private static MainFuncDef instance=new MainFuncDef();
    public static MainFuncDef getInstance(){
        return instance;
    }
    public MainFuncDef parseMainFuncDef(){
//        System.out.println("Parsing MainFuncDef");
        MainFuncDef mainFuncDef=new MainFuncDef();
        mainFuncDef.intToken= Global.parser.match(LexType.INTTK);
        mainFuncDef.mainToken=Global.parser.match(LexType.MAINTK);
        mainFuncDef.leftParent=Global.parser.match(LexType.LPARENT);
        mainFuncDef.rightParent=Global.parser.match(LexType.RPARENT);
//        System.out.println("Parsing block");
        mainFuncDef.block=Block.getInstance().parseBlock();
        return mainFuncDef;
    }
    public void print() throws IOException {
        intToken.print();
        mainToken.print();
        leftParent.print();
        rightParent.print();
        block.print();
        Global.parser.out.write(name+"\n");
    }

}
