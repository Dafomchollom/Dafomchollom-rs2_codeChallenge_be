package exception;

/**
 * @author Dada Oluwashina
 * @since 10/02/2019
 */
public class AuthenticationException extends ProcessException {
    public AuthenticationException(String message) {
        super(ErrorCodes.DEFAULT.getCode(), message);
    }

    public AuthenticationException(String errorCode, String message) {
        super(errorCode, message);
    }
}
