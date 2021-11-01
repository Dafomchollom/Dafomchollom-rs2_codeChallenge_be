package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessRuntimeException extends IconRuntimeException
{
    private String errorCode;

    public ProcessRuntimeException(Throwable t)
    {
        super(t);
    }

    public ProcessRuntimeException(String message)
    {
        super(message);
    }

    public ProcessRuntimeException(String errorCode,String message)
    {
        super(message);

        this.errorCode=errorCode;
    }
}
