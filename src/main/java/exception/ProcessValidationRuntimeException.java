package exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProcessValidationRuntimeException extends IconRuntimeException
{

    private String errorCode;
    private List<ProcessError> processErrors;

    public ProcessValidationRuntimeException(Throwable t)
    {
        super(t);
    }

    public ProcessValidationRuntimeException(String errorCode, String message)
    {
        super(message);

        this.errorCode = errorCode;
    }

    public ProcessValidationRuntimeException(String message, List<ProcessError> errors)
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
