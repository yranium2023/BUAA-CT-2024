import frontend.Error.ErrorHandler;
import frontend.Global;
import frontend.Lexer.Lexer;
import frontend.Parser.Parser;

import java.io.*;

/**
 * @author 吴鹄远
 * @Description
 * @date 2024/9/24 10:19
 */
public class Compiler {
    public static void main(String[] args) throws IOException{
        String inputFileName = "testfile.txt";
        String outputFileName = "lexer.txt";
        String errorFileName = "error.txt";
        String parserFileName = "parser.txt";
        BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));
        BufferedWriter stderr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(errorFileName)));
        BufferedWriter stdPar = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(parserFileName)));
        Lexer lexer = new Lexer(stdin,stdout);
        lexer.file2lex();
        stdin.close();
        lexer.output();
        stdout.close();
        Global.parser = new Parser(lexer,stdPar);
        Global.parser.toParser();
        stdPar.close();
        ErrorHandler.getInstance().printErr(stderr);
        stderr.close();
    }
}
