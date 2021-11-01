package exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class FieldValidationError  implements Serializable

{
    private static final long serialVersionUID = 1L;
    private Object leafBean;
    private String fieldName;
    private Object inputValue;
    private String message;

    public FieldValidationError() {
    }

    public FieldValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public FieldValidationError(Object leafBean, String fieldName, Object inputValue, String message) {
        this.leafBean = leafBean;
        this.fieldName = fieldName;
        this.inputValue = inputValue;
        this.message = message;
    }

    public FieldValidationError(String fieldName, Object inputValue, String message) {
        this.fieldName = fieldName;
        this.inputValue = inputValue;
        this.message = message;
    }
}
