package domain;

import exception.ErrorContext;
import exception.FieldValidationError;
import exception.ProcessError;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApiResponseBase<T>
{

    private T Response;

    private String successMessage;

    private boolean hasError;
    private String errorMessage;
    private String errorCode;

    private ErrorContext errorContext;
    private String errorScope;

    @JsonProperty
    private List<FieldValidationError> fieldValidationErrors;

    @JsonProperty
    private List<ProcessError> processErrors;
}


