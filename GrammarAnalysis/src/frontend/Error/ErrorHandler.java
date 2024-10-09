package frontend.Error;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴鹄远
 * @Description
 * @date 2024/9/29 21:52
 */
public class ErrorHandler {
    private static final ErrorHandler instance=new ErrorHandler();
    public static ErrorHandler getInstance(){
        return instance;
    }
    private List<Error> errorList=new ArrayList<>();

    public void addError(Error newError){
        for(Error error:errorList){
            if(error.equals(newError)){
                return;
            }
        }
        errorList.add(newError);
    }

    public void printErr(BufferedWriter stderr) throws IOException {
        for(Error error:errorList){
            stderr.write(error.toString());
        }
    }
}
