package exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ProcessError implements Serializable
{

    private String errorMessage;
    private String errorCode;
    private String errorType;
    public ProcessError(String errorMessage,String errorCode){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
    public ProcessError(){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    } public ProcessError(String errorMessage){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public ProcessError(String errorMessage, String errorCode, String errorType) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorType = errorType;
    }
}
