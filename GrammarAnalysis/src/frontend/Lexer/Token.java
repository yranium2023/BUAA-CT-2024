package frontend.Lexer;

import frontend.Global;

import java.io.IOException;

/**
 * @author 吴鹄远
 * @Description
 * @date 2024/9/30 11:08
 */
public class Token {
    private int lineNum;
    private String strValue;
    private LexType type;
//    private long intValue;

    public Token(int lineNum, LexType type) {
        this.lineNum = lineNum;
        this.type = type;
    }

    public Token(int lineNum, LexType type, String value) {
        this.lineNum = lineNum;
        this.strValue = value;
        this.type = type;
    }

//    public Token(int lineNum, LexType type, long num) {
//        this.lineNum = lineNum;
//        this.type = type;
//        this.intValue = num;
//    }


    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public LexType getType() {
        return type;
    }

    public void setType(LexType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == LexType.IDENFR || type == LexType.STRCON || type == LexType.CHRCON|| type == LexType.INTCON) {
            return type + " " + strValue + "\n";
        } else {
            return type + " " + type.getSymbol() + "\n";
        }
    }
    public void print() throws IOException{
        Global.parser.out.write(this.toString());
    }
}
