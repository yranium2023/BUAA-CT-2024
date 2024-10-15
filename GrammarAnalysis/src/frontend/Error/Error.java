package frontend.Error;

/**
 * @author 吴鹄远
 * @Description
 * @date 2024/9/30 10:57
 */
public class Error {
    public ErrorType errorType;
    public int lineNum;

    public Error(ErrorType errorType, int lineNum) {
        this.errorType = errorType;
        this.lineNum = lineNum;
    }

    public int getLineNum() {
        return lineNum;
    }

    @Override
    public String toString() {
        return this.lineNum +" "+this.errorType+"\n";
    }
}
