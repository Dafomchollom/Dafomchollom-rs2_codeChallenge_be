/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import domain.ApiResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.InvalidClassException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Dada Oluwashina
 * @since 10/02/2019
 */
@Primary
@Slf4j
@RestControllerAdvice()
public class GlobalControllerExceptionHandler {
    
    @ExceptionHandler(value = {
            PermissionNotGrantedException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> authorizationException(PermissionNotGrantedException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(ex.getMessage());
        log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(value = {
            ObjectOptimisticLockingFailureException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> optimisticLockException(ObjectOptimisticLockingFailureException ex) {
        ex.printStackTrace();
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage("This record has been updated by another process");
        log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            Exception.class,
            RuntimeException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> unknownGeneralException(Exception ex) {
        
        ex.printStackTrace();
        
        if (ex.getClass().getSimpleName().equalsIgnoreCase("AccessDeniedException")) {
            return authException(new AuthenticationException(ex.getMessage()));
        } else if (ex instanceof UndeclaredThrowableException) {
            Throwable undeclaredThrowableException = ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
            if (undeclaredThrowableException instanceof InvocationTargetException) {
                Throwable targetException = ((InvocationTargetException) undeclaredThrowableException).getTargetException();
                return mapException(targetException);
            } else if (undeclaredThrowableException instanceof PermissionNotGrantedException) {
                return authorizationException(new PermissionNotGrantedException(undeclaredThrowableException.getMessage()));
            }
            return unknownException(new IconException(((UndeclaredThrowableException) ex).getCause().getMessage()));
        } else if (ex.getCause() instanceof FieldValidationException) {
            return fieldValidationException(((FieldValidationException) ex.getCause()));
        } else if (ex.getCause() instanceof InvalidClassException) {
            return unknownException(new IconException(((InvalidClassException) ex.getCause()).getMessage()));
        } else if (ex.getCause() instanceof IconQueryException) {
            return queryException(new IconQueryException(((IconQueryException) ex.getCause()).getMessage()));
        } else if (ex instanceof FieldValidationException) {
            return fieldValidationException(((FieldValidationException) ex));
        } else if (ex instanceof CheckSumRuntimeException) {
            return checksumRuntimeException(new CheckSumRuntimeException((CheckSumRuntimeException) ex));
        } else if (ex instanceof IconRuntimeException) {
            return iconRuntimeException(new IconRuntimeException((IconRuntimeException) ex));
        } else if (ex instanceof InvocationTargetException) {
            return unknownException(new IconException(((InvocationTargetException) ex).getCause().getMessage()));
        } else if (ex.getCause() instanceof IconException) {
            return unknownException(new IconException(((IconException) ex.getCause()).getMessage()));
        } else if (ex instanceof IconException) {
            return unknownException(new IconException(((IconException) ex).getMessage()));
        }
    
    
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    private ResponseEntity<ApiResponseBase<?>> mapException(Throwable e) {
        if (e instanceof IconQueryException) {
            return queryException((IconQueryException) e);
        } else if (e instanceof EntityNotFoundException) {
            return entityNotFoundException((EntityNotFoundException) e);
        } else if (e instanceof ProcessException) {
            return processException((ProcessException) e);
        } else if (e instanceof FieldValidationException) {
            return fieldValidationException((FieldValidationException) e);
        } else if (e instanceof ProcessValidationException) {
            return processValidationException((ProcessValidationException) e);
        } else if (e instanceof AuthenticationException) {
            return authException((AuthenticationException) e);
        } else {
            return unknownException((IconException) e);
        }
    }
    
    @ExceptionHandler(value = {
            IconException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> unknownException(IconException ex) {
        ex.printStackTrace();
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
//        log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            CheckSumRuntimeException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> checksumRuntimeException(CheckSumRuntimeException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(ex.getMessage());
//        log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            IconRuntimeException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> iconRuntimeException(IconRuntimeException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(ex.getMessage());
//        log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            IconQueryException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> queryException(IconQueryException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        apiResponseBase.setErrorCode(ex.getErrorCode() + "");
        log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = {
            EntityNotFoundException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> entityNotFoundException(EntityNotFoundException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = {
            ProcessException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> processException(ProcessException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorCode(ex.getErrorCode());
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            FieldValidationException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> fieldValidationException(FieldValidationException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        apiResponseBase.setFieldValidationErrors(ex.getFieldValidationErrors());
        log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            ProcessValidationException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> processValidationException(ProcessValidationException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        apiResponseBase.setProcessErrors(ex.getProcessErrors());
        log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            AuthenticationException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> authException(AuthenticationException ex) {
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorCode(ex.getErrorCode());
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(value = {
            Throwable.class
    })
    protected ResponseEntity<ApiResponseBase<?>> throwable(Throwable e) {
        
        if (e instanceof FieldValidationException) {
            
            FieldValidationException ex = ((FieldValidationException) e);
            log.info("FieldValidationException", ex);
    
            ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
            apiResponseBase.setHasError(true);
            apiResponseBase.setErrorMessage(getErrorMessage(ex));
            apiResponseBase.setFieldValidationErrors(ex.getFieldValidationErrors());
            log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
            return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
        } else if (e instanceof IconException) {
            
            IconException ex = ((IconException) e);
            log.info("IconException", ex);
            
            ex.printStackTrace();
            ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
            apiResponseBase.setHasError(true);
            apiResponseBase.setErrorMessage(getErrorMessage(ex));
//            log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
            return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
        } else if (e instanceof InvocationTargetException) {
            
            InvocationTargetException ex = ((InvocationTargetException) e);
            log.info("InvocationTargetException", ex.getCause());
            
            ex.printStackTrace();
            ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
            apiResponseBase.setHasError(true);
            apiResponseBase.setErrorMessage(ex.getCause().getMessage());
//            log.warn(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
            return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
        }
        log.info("Unknown Throwable", e);
        
        return null;
    }
    
    @ExceptionHandler(value = {
            NonRollbackProcessException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> nonRollbackProcessException(NonRollbackProcessException ex) {
    
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorCode(ex.getErrorCode());
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {
            RollbackProcessException.class
    })
    protected ResponseEntity<ApiResponseBase<?>> rollbackProcessException(RollbackProcessException ex) {
    
        ApiResponseBase<?> apiResponseBase = new ApiResponseBase<>();
        apiResponseBase.setHasError(true);
        apiResponseBase.setErrorCode(ex.getErrorCode());
        apiResponseBase.setErrorMessage(getErrorMessage(ex));
        log.info(apiResponseBase.getErrorMessage(), ex.fillInStackTrace());
        return new ResponseEntity<>(apiResponseBase, HttpStatus.BAD_REQUEST);
    }
    
    private String getErrorMessage(Exception ex) {
        String unhandledErrMsg = "An internal error occurred while processing your request";
        String genericIconErrMsg = "Invalid request value entered";
        String errMsg = "";
        boolean isKnown = ex instanceof IconException;
        boolean isKno = ex instanceof IconException;
        
        if (isKnown) {
            errMsg = StringUtils.hasText(ex.getMessage()) ? ex.getMessage() : genericIconErrMsg;
        } else {
            for (Throwable throwable = ex.getCause(); throwable != null; throwable = throwable.getCause()) {
                // General Json
                if (throwable instanceof JsonMappingException) {
                    errMsg = getJsonErrorMsg((JsonMappingException) throwable);
                    
                    break;
                }
                
                // DATE
                if (throwable instanceof DateTimeParseException) {
                    errMsg = getDateErrorMsg(throwable);
                    
                    break;
                }
                
                if (throwable instanceof InvalidFormatException) {
                    
                    try {
                        Class<?> type = ((InvalidFormatException) throwable).getTargetType();
                        
                        // ENUM
                        if (type instanceof Class && ((Class<?>) type).isEnum()) {
                            errMsg = getEnumErrorMsg(errMsg, (InvalidFormatException) throwable, type);
                        }
                        
                        break;
                    } catch (Exception e) {
                    
                    }
                }
            }
            
            errMsg = StringUtils.hasText(errMsg) ? errMsg : unhandledErrMsg;
        }
        
        return errMsg;
    }
    
    private String getEnumErrorMsg(String errMsg, InvalidFormatException throwable, Class<?> type) {
        Object value = throwable.getValue();
        List<JsonMappingException.Reference> path = throwable.getPath();
        AtomicReference<String> fieldName = new AtomicReference<>("");
        path.forEach(reference -> {
            fieldName.set(reference.getFieldName());
        });
        
        Object[] enums = type.getEnumConstants();
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Object enumName : enums) {
            stringJoiner.add(enumName.toString());
        }
        errMsg = String.format("Wrong value '%s' for '%s', expected values are [%s]", value, fieldName.get(), stringJoiner.toString());
        return errMsg;
    }
    
    private String getDateErrorMsg(Throwable throwable) {
        String errMsg;
        errMsg = String.format("Wrong Date/Time format %s", throwable.getMessage().substring(throwable.getMessage().indexOf("'"), throwable.getMessage().lastIndexOf("'")));
        return errMsg;
    }
    
    
    private String getJsonErrorMsg(JsonMappingException jsonMappingException) {
        
        return String.format("Wrong input format: %s", jsonMappingException.getMessage().substring(0, jsonMappingException.getMessage().indexOf("):")));
    }
    
}
