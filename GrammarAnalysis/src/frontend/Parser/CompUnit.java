package frontend.Parser;

import frontend.Parser.Decl.Decl;
import frontend.Parser.Func.FuncDef;
import frontend.Parser.Func.MainFuncDef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 编译单元 CompUnit → {Decl} {FuncDef} MainFuncDef // 1.是否存在Decl 2.是否存在FuncDef
 * @date 2024/10/9 20:42
 */
public class CompUnit {
    public final String name="<CompUnit>";
//    public final boolean isTerminal=false;
    public List<Decl> decls;
    public List<FuncDef> funcDefs;
    public MainFuncDef mainFuncDef;
    public CompUnit(){
        decls=new ArrayList<>();
        funcDefs=new ArrayList<>();
        parseDecls();
        parseFuncDefs();
        parseMainFuncDef();
    }
    public void parseDecls(){

    }

    public void parseFuncDefs(){

    }

    public void parseMainFuncDef(){

    }

}
