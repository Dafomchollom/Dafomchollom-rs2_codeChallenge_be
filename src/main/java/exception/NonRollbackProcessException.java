package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonRollbackProcessException extends ProcessException
{

    public NonRollbackProcessException(Throwable t)
    {
        super(t);
    }

    public NonRollbackProcessException(String errorCode, String message)
    {
        super(errorCode, message);
    }
}




