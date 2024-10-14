package frontend.Lexer;

import frontend.Error.Error;
import frontend.Error.ErrorHandler;
import frontend.Error.ErrorType;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description 词法分析主类
 * @date 2024/9/24 10:24
 */
public class Lexer {
    private BufferedReader sourceReader;
    private BufferedWriter outputWriter;
    String curLine;
    public ArrayList<Character> source;
    char ch;
    private int curPos;
    private int curLineNum;
    private boolean rowNote=false; //单行注释
    private boolean multiNote=false; //多行注释
    public List<Token> tokenList=new ArrayList<>();
    public static final HashMap<String, LexType> str2lex = new HashMap<>();

    static {
        for (LexType type : LexType.values()) {
            // 只加入那些有符号表示的类型
            if (type.getSymbol() != null) {
                str2lex.put(type.getSymbol(),type);
            }
        }
    }


    public Lexer(BufferedReader sourceReader,BufferedWriter outputWriter){
        this.sourceReader=sourceReader;
        this.outputWriter=outputWriter;
        this.curLineNum=0;
    }
    //从流中读取字符串
    public void file2lex() throws IOException{
        while((curLine=sourceReader.readLine())!=null){
            System.out.println(curLine);
            //更新行号
            curLineNum++;
            rowNote=false;
            //需要先处理掉注释
            for(curPos=0;curPos<curLine.length();curPos++){
                if(rowNote){
                    break;
                }else if (multiNote){
                    int end;
                    if((end=curLine.indexOf("*/",curPos))==-1){
                        break;
                    }else{
                        curPos=end+2;
                        multiNote=false;
                        if(curPos>=curLine.length()){
                            break;
                        }
                    }
                }
                ch=curLine.charAt(curPos);
                if(ch == ' '||ch == '\r'||ch == '\t'||ch == '\n'){
                    continue;
                }
                if(Character.isLowerCase(ch)||Character.isUpperCase(ch)||ch=='_'){
                    judgeIDENFR();
                }else if(Character.isDigit(ch)){
                    judgeINTCON();
                }else if(ch=='\"'|| ch=='\''){
                    judgeSTRCHRCON();
                }
                else if(ch=='/'){
                    judgeNote();
                }else{
                    judgeOTHER();
                }

            }
        }
    }
    //识别标识符/保留字
    public void judgeIDENFR() throws IOException{
        String str=new String();
        str+=ch;
        while(++curPos<curLine.length()){
            ch=curLine.charAt(curPos);
            if(Character.isLowerCase(ch)||Character.isUpperCase(ch)
                    ||Character.isDigit(ch)||ch=='_'){
                str+=ch;
            }else{
                curPos--;
                break;
            }
        }
        tokenList.add(new Token(curLineNum,str2lex.getOrDefault(str, LexType.IDENFR),str));
//        outputWriter.write(Token.getInstance().getLexCode(str));
    }
    //识别无符号整数
    public void judgeINTCON() throws IOException {
        StringBuilder numStr = new StringBuilder();  // 使用 StringBuilder 来存储数字字符串
        numStr.append(ch);  // 将第一个字符存入字符串

        while (++curPos < curLine.length()) {
            ch = curLine.charAt(curPos);
            if (Character.isDigit(ch)) {
                numStr.append(ch);  // 追加数字字符
            } else {
                curPos--;
                break;
            }
        }

        // 在需要时可以将字符串转为数字，也可直接存储字符串
        tokenList.add(new Token(curLineNum, LexType.INTCON, numStr.toString()));
    }


    // 识别字符串常量并处理转义字符
    public void judgeSTRCHRCON() throws IOException {
        StringBuilder str = new StringBuilder();
        str.append(ch); // 添加第一个引号
        boolean isSTR = (ch == '\"');
        boolean escape = false; // 标记是否在处理转义字符

        while (++curPos < curLine.length()) {
            ch = curLine.charAt(curPos);

            if (escape) {
                // 处理转义字符
                switch (ch) {
                    case 'n':
                        str.append("\\n"); // 换行符
                        break;
                    case 't':
                        str.append("\\t"); // 制表符
                        break;
                    case 'r':
                        str.append("\\r"); // 回车符
                        break;
                    case 'b':
                        str.append("\\b"); // 退格符
                        break;
                    case 'f':
                        str.append("\\f"); // 换页符
                        break;
                    case '\"':
                        str.append('\"'); // 引号
                        break;
                    case '\\':
                        str.append('\\'); // 反斜杠
                        break;
                    default:
                        // 非法转义字符，可以根据需求处理异常
                        str.append("\\"+ch); // 如果不是有效的转义字符，就直接添加原字符
                        break;
                }
                escape = false; // 处理完一个转义字符，重置标志
            } else if (ch == '\\') {
                // 如果遇到反斜杠，标记为进入转义字符模式
                escape = true;
            } else if (ch == '\"'&& isSTR) {
                // 如果遇到结束的引号，结束字符串处理
                str.append(ch);
                break;
            }else if(ch == '\''&& !isSTR) {
                str.append(ch);
                break;
            }else {
                // 普通字符，直接添加
                str.append(ch);
            }
        }
        if(isSTR){
            tokenList.add(new Token(curLineNum,LexType.STRCON,str.toString()));
        }else{
            tokenList.add(new Token(curLineNum,LexType.CHRCON,str.toString()));
        }
//        outputWriter.write("STRCON " + str + "\n");
    }

    public void judgeNote() throws IOException{
        ch=curLine.charAt(++curPos);
        if(ch=='/'){
            rowNote=true;
        }else if(ch=='*'){
            multiNote=true;
        }else{
            curPos--;
            tokenList.add(new Token(curLineNum,str2lex.get("/")));
//            outputWriter.write(Token.getInstance().getLexCode('/'));
        }

    }
    //识别其他情况
    public void judgeOTHER() throws IOException{
        //||和&&的情况
        if(ch=='|'||ch=='&'){
            if(++curPos<curLine.length()&&curLine.charAt(curPos)==ch){
                tokenList.add(new Token(curLineNum,str2lex.get(String.valueOf(ch)+ch)));
//                outputWriter.write(Token.getInstance().getLexCode(String.valueOf(ch)+ch));
            }else{
                curPos--;
                tokenList.add(new Token(curLineNum,str2lex.get(String.valueOf(ch)+ch)));
//                outputWriter.write(Token.getInstance().getLexCode(ch));
                ErrorHandler.getInstance().addError(new Error(ErrorType.ILLEGAL_CHAR,curLineNum));
            }
        }
        else if(ch=='<'||ch=='>'||ch=='='||ch=='!'){
            if(++curPos<curLine.length()&&curLine.charAt(curPos)=='='){
                tokenList.add(new Token(curLineNum,str2lex.get(ch+"=")));
//                outputWriter.write(Token.getInstance().getLexCode(ch+"="));
            }else{
                curPos--;
                tokenList.add(new Token(curLineNum,str2lex.get(String.valueOf(ch))));
//                outputWriter.write(Token.getInstance().getLexCode(ch));
            }
        }else {
            tokenList.add(new Token(curLineNum,str2lex.get(String.valueOf(ch))));
//            outputWriter.write(Token.getInstance().getLexCode(ch));
        }
    }

    public void output() throws IOException{
        for(Token token:tokenList){
            outputWriter.write(token.toString());
        }

    }


}
