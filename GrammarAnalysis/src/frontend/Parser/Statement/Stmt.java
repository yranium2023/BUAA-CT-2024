package frontend.Parser.Statement;

/**
 * @author 吴鹄远
 * @Description
 * 语句 Stmt → LVal '=' Exp ';' // 每种类型的语句都要覆盖
 * | [Exp] ';' //有无Exp两种情况
 * | Block
 * | 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // 1.有else 2.无else
 * | 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt // 1. 无缺省，1种情况 2.ForStmt与Cond中缺省一个，3种情况 3. ForStmt与Cond中缺省两个，3种情况 4. ForStmt与Cond全部缺省，1种情况
 * | 'break' ';' | 'continue' ';'
 * | 'return' [Exp] ';' // 1.有Exp 2.无Exp
 * | LVal '=' 'getint''('')'';'
 * | LVal '=' 'getchar''('')'';'
 * | 'printf''('StringConst {','Exp}')'';' // 1.有Exp 2.无Exp
 * @date 2024/10/9 21:13
 */
public class Stmt {
}
