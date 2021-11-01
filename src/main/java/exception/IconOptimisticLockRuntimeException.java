package exception;

public class IconOptimisticLockRuntimeException extends RuntimeException
{

    public IconOptimisticLockRuntimeException(String message)
    {
        super(message);
    }

    public IconOptimisticLockRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public IconOptimisticLockRuntimeException(Throwable cause)
    {
        super(cause);
    }

    public IconOptimisticLockRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
