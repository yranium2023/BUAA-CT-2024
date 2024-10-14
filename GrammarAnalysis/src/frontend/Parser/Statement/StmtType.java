package frontend.Parser.Statement;

/**
 * @author 吴鹄远
 * @Description
 * 语句 Stmt → LVal '=' Exp ';'   //LValAssignExp
 * | [Exp] ';'  //i
 * | Block
 * | 'if' '(' Cond ')' Stmt [ 'else' Stmt ] //j
 * | 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
 * | 'break' ';'    //i
 * | 'continue' ';'   //i
 * | 'return' [Exp] ';'     //i
 * | LVal '=' 'getint''('')'';'     //ij
 * | LVal '=' 'getchar''('')'';'    //ij
 * | 'printf' '(' StringConst { ',' Exp } ')' ';'  //ij
 * @date 2024/10/14 22:18
 */
public enum StmtType {
    LValAssignExp,
    Exp,
    Block,
    If,
    For,
    Break,
    Continue,
    Return,
    LvalAssignGetint,
    LvalAssignGetchar,
    Printf

}
