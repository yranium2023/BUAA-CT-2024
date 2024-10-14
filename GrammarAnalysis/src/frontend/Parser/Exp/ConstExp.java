package frontend.Parser.Exp;

import frontend.Parser.Decl.ConstDef;

/**
 * @author 吴鹄远
 * @Description 常量表达式 ConstExp → AddExp 注：使用的 Ident 必须是常量
 * @date 2024/10/9 21:15
 */
public class ConstExp {
    private AddExp addExp=null;
    private ConstExp(){

    }
    private static ConstExp instance=new ConstExp();
    public static ConstExp getInstance(){
        return instance;
    }
    public ConstExp parseConstExp(){
        ConstExp constExp=new ConstExp();
        constExp.addExp=AddExp.getInstance().parseAddExp();
        return constExp;
    }

}
