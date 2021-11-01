package exception;

public class IconRuntimeException extends RuntimeException
{

    public IconRuntimeException(String message)
    {
        super(message);
    }

    public IconRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public IconRuntimeException(Throwable cause)
    {
        super(cause);
    }

    public IconRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
