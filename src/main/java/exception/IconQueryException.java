package exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IconQueryException extends IconException
{

    private String entity;
    private String errorCode;

    public IconQueryException(Throwable throwable)
    {
        super(throwable);
    }

    public IconQueryException(String message)
    {
        super(message);
    }

    public IconQueryException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }


    public IconQueryException(String errorCode, String message, String entity)
    {
        super(message);
        this.errorCode = errorCode;
        this.entity = entity;
    }


}



