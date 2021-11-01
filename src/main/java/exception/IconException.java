package exception;

public class IconException extends Exception {
    private static final long serialVersionUID = 1L;

    public IconException(String message) {
        super(message);
    }

    public IconException(String message, Throwable cause) {
        super(message, cause);
    }

    public IconException(Throwable cause) {
        super(cause);
    }

    public IconException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
