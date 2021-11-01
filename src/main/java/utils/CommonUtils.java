package utils;

import domain.ApiResponseBase;
import domain.PageQueryCriteria;
import exception.FieldValidationError;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonUtils {
    public static <T> HttpStatus getHttpStatus(ApiResponseBase<T> rsp) {
        HttpStatus status = HttpStatus.OK;

        if (rsp.isHasError()) {
            if (!rsp.getErrorScope().isEmpty() &&
                    rsp.getErrorScope().equalsIgnoreCase("find")
                    || rsp.getErrorScope().equalsIgnoreCase("query")

            ) {
                status = HttpStatus.NOT_FOUND;

            }

            if (rsp.getFieldValidationErrors() != null) {
                if (!rsp.getFieldValidationErrors().isEmpty()) {
                    status = HttpStatus.UNPROCESSABLE_ENTITY;
                }
            }

        }

        return status;
    }


//    public static Pageable getPageable(@NotNull PageQueryCriteria criteria) {
//        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "Id"));
//        if (criteria.getSortOrder().toUpperCase().equals("ASC")) {
//            sort = Sort.by(new Sort.Order(Sort.Direction.ASC, criteria.getOrderBy()));
//        }
//        if (criteria.getSortOrder().toUpperCase().equals("DESC")) {
//            sort = Sort.by(new Sort.Order(Sort.Direction.DESC, criteria.getOrderBy()));
//        }
//
//        Pageable pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize(), sort);
//
//        return pageable;
//
//    }

    /**
     * Validates fields against their annotated constraints
     *
     * @param data      the object to validate
     * @param validator the validator, an implementation of {@link Validator}
     * @param <T>       the type of object to validate
     * @return List<FieldValidationError>
     * @deprecated use {@link #getStaticFieldValidationErrors}
     */
    @Deprecated
    public static <T> List<FieldValidationError> getDefaultFieldValidationErrors(@NotNull T data, Validator validator) {

        Set<ConstraintViolation<T>> validationErrors = validator.validate(data);
        List<FieldValidationError> fieldValidationErrors = new ArrayList<>();

        for (ConstraintViolation constraint : validationErrors) {

            FieldValidationError error = new FieldValidationError();
            error.setFieldName(constraint.getPropertyPath().toString());
            error.setMessage(constraint.getMessage());
            error.setInputValue(constraint.getInvalidValue());

            fieldValidationErrors.add(error);
        }
        return fieldValidationErrors;
    }


    public static List<FieldValidationError> getFieldBindingErrors(BindingResult bindingResult) {

        List<FieldValidationError> fieldValidationErrors = new ArrayList<>();

        for (FieldError constraint : bindingResult.getFieldErrors()) {

            FieldValidationError error = new FieldValidationError();
            error.setFieldName(constraint.getField());
            error.setInputValue(error.getInputValue());
            error.setMessage(constraint.getDefaultMessage());

            fieldValidationErrors.add(error);
        }
        return fieldValidationErrors;
    }

    /**
     * Validates fields against their annotated constraints
     *
     * @param data      the object to validate
     * @param validator the validator, an implementation of {@link Validator}
     * @param <T>       the type of object to validate
     * @return List<FieldValidationError>
     */
    public static <T> List<FieldValidationError> getStaticFieldValidationErrors(@NotNull T data, Validator validator) {

        return validator.validate(data).parallelStream().map(constraint -> {
            return new FieldValidationError(
                    constraint.getLeafBean().getClass().getName(),
                    constraint.getPropertyPath().toString(),
                    constraint.getInvalidValue(),
                    constraint.getMessage()
            );
        }).collect(Collectors.toList());
    }
}
