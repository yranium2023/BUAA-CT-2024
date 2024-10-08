package frontend.Error;

/**
 * @author 吴鹄远
 * @Description
 * @date 2024/9/29 21:52
 */
public enum ErrorType {
    ILLEGAL_CHAR("a"),
    DUPLICATED_IDENT("b"),
    UNDEFINED_IDENT("c"),
    MISMATCH_PARAM_NUM("d"),
    MISMATCH_PARAM_TYPE("e"),
    RETURN_VALUE_VOID("f"),
    MISSING_RETURN("g"),
    ALTER_CONST("h"),
    MISSING_SEMICN("i"),
    MISSING_R_PARENT("j"),
    MISSING_R_BACKET("k"),
    MISMATCCH_PRINTF("l"),
    MISUSE_END_LOOP("m");
    private String code;

    ErrorType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    // 重写 toString 方法，返回 code
    @Override
    public String toString() {
        return this.code;
    }
}
