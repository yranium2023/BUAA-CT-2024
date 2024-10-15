package frontend.Parser;

import frontend.Global;
import frontend.Lexer.LexType;
import frontend.Parser.Decl.Decl;
import frontend.Parser.Func.FuncDef;
import frontend.Parser.Func.MainFuncDef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * 编译单元 CompUnit → {Decl} {FuncDef} MainFuncDef // 1.是否存在Decl 2.是否存在FuncDef
 * @date 2024/10/9 20:42
 */
public class CompUnit {
    private static final String name="<CompUnit>";
//    public final boolean isTerminal=false;
    private List<Decl> decls=new ArrayList<>();
    private List<FuncDef> funcDefs=new ArrayList<>();
    private MainFuncDef mainFuncDef=null;
    private CompUnit(){
    }
    private static CompUnit instance=new CompUnit();
    public static CompUnit getInstance(){
        return instance;
    }
    public CompUnit parseCompUnit(){
        CompUnit compUnit=new CompUnit();
        while (!Global.parser.preReadToken(2).getType().equals(LexType.MAINTK)
        && !Global.parser.preReadToken(3).getType().equals(LexType.LPARENT)){
            compUnit.decls.add(Decl.getInstance().parseDecl());
        }
        while (!Global.parser.preReadToken(2).getType().equals(LexType.MAINTK)){
            compUnit.funcDefs.add(FuncDef.getInstance().parseFuncDef());
        }
        compUnit.mainFuncDef=MainFuncDef.getInstance().parseMainFuncDef();
        return compUnit;
    }
    public void print() throws IOException {
        for(int i=0;i<decls.size();i++){
            decls.get(i).print();
        }
        for(int i=0;i<funcDefs.size();i++){
            funcDefs.get(i).print();
        }
        mainFuncDef.print();
        Global.parser.out.write(name+"\n");
    }

}
