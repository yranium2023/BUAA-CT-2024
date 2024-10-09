import frontend.Error.ErrorHandler;
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
        lexer.output();
        Parser parser = new Parser(lexer,stdPar);
        ErrorHandler.getInstance().printErr(stderr);
        stdin.close();
        stdout.close();
        stderr.close();
    }
}
