package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IconQueryRuntimeException extends IconRuntimeException
{

    private String entity;
    private String errorCode;

    public IconQueryRuntimeException(Throwable throwable)
    {
        super(throwable);
    }

    public IconQueryRuntimeException(String message)
    {
        super(message);
    }

    public IconQueryRuntimeException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }


    public IconQueryRuntimeException(String errorCode, String message, String entity)
    {
        super(message);
        this.errorCode = errorCode;
        this.entity = entity;
    }


}
