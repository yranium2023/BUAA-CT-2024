package frontend.Parser.Statement;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;
import frontend.Parser.Decl.Decl;

import javax.sound.sampled.Port;
import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * 语句块项 BlockItem → Decl | Stmt
 * @date 2024/10/9 21:12
 */
public class BlockItem {
    private static final String name="<BlockItem>";
    private Decl decl=null;
    private Stmt stmt=null;
    private BlockItem(){}
    private static BlockItem instance=new BlockItem();
    public static BlockItem getInstance(){
        return instance;
    }
    public BlockItem parseBlockItem(){
        BlockItem blockItem=new BlockItem();
        Token token= Global.parser.preReadToken();
        if(token.getType().equals(LexType.CONSTTK)
        || token.getType().equals(LexType.INTTK)
        || token.getType().equals(LexType.CHARTK)){
            blockItem.decl=Decl.getInstance().parseDecl();
        }else{
            blockItem.stmt=Stmt.getInstance().parseStmt();
        }
        return  blockItem;
    }
    public void print() throws IOException {
        if(decl!=null){
            decl.print();
        }else{
            stmt.print();
        }
    }
}
