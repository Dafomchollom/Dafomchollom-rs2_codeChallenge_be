package exception;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FieldValidationRuntimeException extends IconRuntimeException
{

    private List<FieldValidationError> fieldValidationErrors;


    public FieldValidationRuntimeException(List<FieldValidationError> fieldValidationErrors)
    {
        super("");
        this.fieldValidationErrors = fieldValidationErrors;
    }

    public FieldValidationRuntimeException(Throwable throwable, List<FieldValidationError> fieldValidationErrors)
    {
        super(throwable);
        this.fieldValidationErrors = fieldValidationErrors;
    }

    public FieldValidationRuntimeException(String message, List<FieldValidationError> fieldValidationErrors)
    {
        super(message);
        this.fieldValidationErrors = fieldValidationErrors;
    }

    public void addError(FieldValidationError error)
    {
        if (fieldValidationErrors == null)
        {
            fieldValidationErrors = new ArrayList<>();
        }
        fieldValidationErrors.add(error);
    }
}



