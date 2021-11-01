package exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServiceException extends IconException
{
    private String errorCode;

    public ServiceException(Throwable t)
    {
        super(t);
    }

    public ServiceException(String errorCode, String message)
    {
        super(message);

        this.errorCode = errorCode;
    }


    public ServiceException(String message)
    {
        super(message);
    }
}
