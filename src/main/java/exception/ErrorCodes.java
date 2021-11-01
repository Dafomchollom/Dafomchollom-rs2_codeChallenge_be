package exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    DEFAULT("001", ""),
    DUPLICATE_EXISTS("9000", ""),
    EXISTS_ON_BLACKLIST("9999", ""),
    SESSION_TOKEN_EXPIRED("7777", ""),
    ACTIVE_SESSION_EXISTS("6000", ""),
    SESSION_TIMED_OUT("6666", ""),
    TWO_FA_REQUIRED("5555", ""),
    ALERT_USER_FOR_CONFIRMATION("5000", ""),
    DATE_CHANGE_IN_PROGRESS("4444", "");

    private String code;
    private String description;

    ErrorCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
