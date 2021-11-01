package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundRuntimeException extends IconRuntimeException {

    private Long id;
    private String entityType;
    private String errorCode;
    private ErrorContext errorContext = ErrorContext.READ;


    public EntityNotFoundRuntimeException(Long id) {
        super("Could not find entity with Id: " + id);
        this.id = id;
    }

    public EntityNotFoundRuntimeException(Long id, String entityType) {
        super("Could not find entity with Id: " + id);
        this.id = id;
        this.entityType = entityType;
    }


    public EntityNotFoundRuntimeException(Throwable t) {
        super(t);
    }

    public EntityNotFoundRuntimeException(String message, String errorCode) {
        super(message);

        this.errorCode = errorCode;
    }

    public EntityNotFoundRuntimeException(String message) {
        super(message);
    }
}
