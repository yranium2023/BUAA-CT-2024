package frontend.Parser;

import frontend.Lexer.Lexer;
import frontend.Lexer.Token;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * @date 2024/10/8 19:42
 */
public class Parser {
    public Lexer lexer;
    public BufferedWriter out;
    int index;
    public List<Token> tokenList=new ArrayList<>();

    public Parser(Lexer lexer, BufferedWriter out) {
        this.lexer = lexer;
        this.out = out;
        tokenList=lexer.tokenList;
        index = -1;
    }
}
