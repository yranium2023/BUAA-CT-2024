package frontend.Parser.Exp;

/**
 * @author 吴鹄远
 * @Description
 * 条件表达式 Cond → LOrExp
 * @date 2024/10/9 21:13
 */
public class Cond {
    private LOrExp lOrExp=null;
    private Cond(){}
    private static Cond instance=new Cond();
    public static Cond getInstance(){
        return instance;
    }
    public Cond parseCond(){
        Cond cond=new Cond();
        cond.lOrExp=LOrExp.getInstance().parseLOrExp();
        return cond;
    }
}
