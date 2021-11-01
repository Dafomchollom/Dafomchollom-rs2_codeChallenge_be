package exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class SystemError  implements Serializable
{
    private String processName;

    private String scope;
    private String context;

    private boolean errorFlag;
    private String errorMessage;
    private String errorCode;
    private String errorType;

    private String validationMessage;
    private boolean validFlag;

}
