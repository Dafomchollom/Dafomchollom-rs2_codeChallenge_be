package exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ProcessValidationException extends IconException
{

    private String errorCode;
    private List<ProcessError> processErrors;

    public ProcessValidationException(Throwable t)
    {
        super(t);
    }

    public ProcessValidationException(String errorCode, String message)
    {
        super(message);

        this.errorCode = errorCode;
    }

    public ProcessValidationException(String message, List<ProcessError> errors)
    {
        super(message);
        processErrors = errors;
    }

    public void addError(ProcessError error)
    {
        if (processErrors == null)
        {
            processErrors = new ArrayList<>();
        }
        processErrors.add(error);
    }
}








