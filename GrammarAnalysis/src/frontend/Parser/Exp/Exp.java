package frontend.Parser.Exp;

/**
 * @author 吴鹄远
 * @Description 表达式 Exp → AddExp // 存在即可
 * @date 2024/10/9 21:13
 */
public class Exp {
    private static Exp instance=new Exp();
    private AddExp addExp=null;
    private Exp(){

    }
    public static Exp getInstance(){
        return instance;
    }
    public Exp parseExp(){
        Exp exp=new Exp();
        exp.addExp=AddExp.getInstance().parseAddExp();
        return exp;
    }
}
