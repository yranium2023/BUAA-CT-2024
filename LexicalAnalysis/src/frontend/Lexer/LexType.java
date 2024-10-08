package frontend.Lexer;

import java.util.HashMap;

/**
 * @author 吴鹄远
 * @Description 单词类型枚举类
 * @date 2024/9/27 19:16
 */
public enum LexType {
    IDENFR(null), INTCON(null), STRCON(null), CHRCON(null),  // 字面量无对应符号
    MAINTK("main"), CONSTTK("const"), INTTK("int"), CHARTK("char"), VOIDTK("void"),
    BREAKTK("break"), CONTINUETK("continue"), IFTK("if"), ELSETK("else"),
    FORTK("for"), GETINTTK("getint"), GETCHARTK("getchar"), PRINTFTK("printf"), RETURNTK("return"),
    NOT("!"), AND("&&"), OR("||"), PLUS("+"), MINU("-"),
    MULT("*"), DIV("/"), MOD("%"), LSS("<"), LEQ("<="), GRE(">"), GEQ(">="), EQL("=="), NEQ("!="),
    ASSIGN("="), SEMICN(";"), COMMA(","),
    LPARENT("("), RPARENT(")"), LBRACK("["), RBRACK("]"), LBRACE("{"), RBRACE("}");
    // 用于存储对应的字符串
    private final String symbol;

    // 构造函数
    LexType(String symbol) {
        this.symbol = symbol;
    }

    // 获取对应的字符串表示
    public String getSymbol() {
        return symbol;
    }

    // 根据字符串查找对应的 LexType
    public static LexType fromString(String str) {
        for (LexType type : LexType.values()) {
            if (str.equals(type.symbol)) {
                return type;
            }
        }
        return null; // 如果没有找到，返回 null 或者可以抛出异常
    }
}

