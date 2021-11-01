package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends IconException {

    private Long id;
    private String entityType;
    private String errorCode;
    private ErrorContext errorContext = ErrorContext.READ;

    public EntityNotFoundException(Long id) {
        super("Could not find entity with Id: " + id);
        this.id = id;
    }

    public EntityNotFoundException(Long id, String entityType) {
        super(String.format("Could not find '%s' with ID '%s'", entityType, id));
        this.id = id;
        this.entityType = entityType;
    }


    public EntityNotFoundException(Throwable t) {
        super(t);
    }

    public EntityNotFoundException(String message, String errorCode) {
        super(message);

        this.errorCode = errorCode;
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public String toString() {
        return "EntityNotFoundException(id= " + this.getId() + ", entityType= " + this.getEntityType() + ", errorCode= " + this.getErrorCode() + ", " +
                "errorContext= " + this.getErrorContext() + ", errorMessage = " + this.getMessage() + ")";
    }
}



