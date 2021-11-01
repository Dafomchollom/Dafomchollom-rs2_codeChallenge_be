package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionNotGrantedException extends IconException {
    private String detail;
    
    public PermissionNotGrantedException(String msg) {
        super(msg);
    }
    
    public PermissionNotGrantedException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public PermissionNotGrantedException(String msg, String detail, Throwable t) {
        super(msg, t);
        this.detail = detail;
    }
    
}