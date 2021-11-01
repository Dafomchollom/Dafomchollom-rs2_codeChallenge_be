package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RollbackProcessException extends ProcessException
{

    public RollbackProcessException(Throwable t)
    {
        super(t);
    }

    public RollbackProcessException(String errorCode, String message)
    {
        super(errorCode, message);
    }
}




