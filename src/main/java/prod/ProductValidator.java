package prod;

import exception.FieldValidationError;
import exception.FieldValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import prod.dto.AddProductDTO;
import prod.dto.UpdateProductDTO;
import utils.CommonUtils;

import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/***
 * @author : Dada Oluwashina
 * @since : 18/07/2019
 */

@RequiredArgsConstructor
@Component
public class ProductValidator {

    private final Validator validator;


    public void validateInsert(final AddProductDTO dto) throws FieldValidationException {
        List<FieldValidationError> fieldValidationErrors = CommonUtils.getStaticFieldValidationErrors(dto, validator);

        if (!fieldValidationErrors.isEmpty()) {
            throw new FieldValidationException("Insert validation failed", fieldValidationErrors);

        }


    }

    public void validateBatchInsert(@NotEmpty final List<AddProductDTO> serviceDTOS, Long companyId) throws FieldValidationException {
        List<FieldValidationError> fieldValidationErrors = CommonUtils.getStaticFieldValidationErrors(serviceDTOS, validator);

        if (!fieldValidationErrors.isEmpty()) {
            throw new FieldValidationException("Account service validation failed", fieldValidationErrors);

        }


    }



    public void validateUpdate(final Long id,final UpdateProductDTO dto) throws FieldValidationException {
        List<FieldValidationError> fieldValidationErrors = CommonUtils.getStaticFieldValidationErrors(dto, validator);

        if (!fieldValidationErrors.isEmpty()) {
            throw new FieldValidationException("Update validation failed", fieldValidationErrors);

        }

    }






    private void validateAccountServiceValidity(List<FieldValidationError> fieldValidationErrors, UpdateProductDTO dto, String accountId) {
//        Boolean wrapper = entityRepo.existsByAccountIdAndServiceTypeAndIdIsNot(accountId, dto.getServiceType(), dto.getId());
//        if (wrapper) {
//            FieldValidationError error = new FieldValidationError();
//            error.setFieldName("serviceType");
//            error.setMessage("ServiceType already exist -> " + dto.getServiceType());
//            fieldValidationErrors.add(error);
//        }
    }



}
 