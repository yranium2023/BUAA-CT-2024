package frontend.Parser.Statement;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Lexer.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 语句块 Block → '{' { BlockItem } '}'
 * @date 2024/10/9 21:12
 */
public class Block {
    private static final String name="<Block>";
    private Token leftBrace=null;
    private List<BlockItem> blockItems=new ArrayList<>();
    private Token rightBrace=null;
    private Block(){}
    private static Block instance=new Block();
    public static Block getInstance(){
        return instance;
    }
    public Block parseBlock(){
        Block block=new Block();
        block.leftBrace= Global.parser.match(LexType.LBRACE);
        while(!Global.parser.preReadToken().getType().equals(LexType.RBRACE)){
            block.blockItems.add(BlockItem.getInstance().parseBlockItem());
        }
        block.rightBrace=Global.parser.match(LexType.RBRACE);
        return block;
    }
    public void print() throws IOException {
        leftBrace.print();
//        System.out.println(blockItems.size());
        for(int i=0;i<blockItems.size();i++){
            blockItems.get(i).print();
        }
        rightBrace.print();
        Global.parser.out.write(name+"\n");
    }
}
