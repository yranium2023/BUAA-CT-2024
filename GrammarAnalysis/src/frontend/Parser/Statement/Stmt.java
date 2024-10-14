package frontend.Parser.Statement;

/**
 * @author 吴鹄远
 * @Description
 * 语句 Stmt → LVal '=' Exp ';'   //i
 * | [Exp] ';'  //i
 * | Block
 * | 'if' '(' Cond ')' Stmt [ 'else' Stmt ] //j
 * | 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
 * | 'break' ';' | 'continue' ';'   //i
 * | 'return' [Exp] ';'     //i
 * | LVal '=' 'getint''('')'';'     //ij
 * | LVal '=' 'getchar''('')'';'    //ij
 * | 'printf''('StringConst {','Exp}')'';'  //ij
 * @date 2024/10/9 21:13
 */
public class Stmt {
}
