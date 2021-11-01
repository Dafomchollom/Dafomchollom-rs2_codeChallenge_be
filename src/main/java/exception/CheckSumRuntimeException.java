package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckSumRuntimeException extends IconQueryRuntimeException
{

    private String entity;
    private String errorCode;

    public CheckSumRuntimeException(Throwable throwable)
    {
        super(throwable);
    }

    public CheckSumRuntimeException(String message)
    {
        super(message);
    }

    public CheckSumRuntimeException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }


    public CheckSumRuntimeException(String errorCode, String message, String entity)
    {
        super(message);
        this.errorCode = errorCode;
        this.entity = entity;
    }


}
