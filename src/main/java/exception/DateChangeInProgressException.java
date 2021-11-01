package exception;

/**
 * @author Dada Oluwashina
 * @since 10/02/2019
 */
public class DateChangeInProgressException extends ProcessException {
    public DateChangeInProgressException(String message) {
        super(ErrorCodes.DATE_CHANGE_IN_PROGRESS.getCode(), message);
    }

    public DateChangeInProgressException(String errorCode, String message) {
        super(errorCode, message);
    }
}
