package exception;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dada Oluwashina
 * @since 10/02/2019
 */
@Getter
@Setter
@ToString
public class FieldValidationException extends IconException
{
    private static final long serialVersionUID = 1L;
    private List<FieldValidationError> fieldValidationErrors;


    public FieldValidationException(List<FieldValidationError> fieldValidationErrors)
    {
        super("");
        this.fieldValidationErrors = fieldValidationErrors;
    }

    public FieldValidationException(Throwable throwable, List<FieldValidationError> fieldValidationErrors)
    {
        super(throwable);
        this.fieldValidationErrors = fieldValidationErrors;
    }

    public FieldValidationException(String message, List<FieldValidationError> fieldValidationErrors)
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



