package prod;

import domain.PageQueryCriteria;
import exception.EntityNotFoundException;
import exception.FieldValidationException;
import exception.IconQueryException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import prod.dto.AddProductDTO;
import prod.dto.ProductDTO;
import prod.dto.UpdateProductDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

/***
 * @author :  Dada Oluwashina
 * @since : 18/07/2019
 */


public interface ProductService
{


    ProductDTO createOne(final AddProductDTO dto)
            throws FieldValidationException, EntityNotFoundException;


    List<ProductDTO> createBatch(@NotEmpty List<AddProductDTO> dtos, Long companyId) throws FieldValidationException, EntityNotFoundException;

    ProductDTO update(final Long id, final UpdateProductDTO dto)
            throws FieldValidationException, EntityNotFoundException;


    Boolean deleteById(@Positive final Long id) throws FieldValidationException, EntityNotFoundException;


    ProductDTO findById(@Positive final Long id) throws IconQueryException;


    List<ProductDTO> findByCategoryId(@NotBlank String companyId);

    Page<ProductDTO> listAll(Pageable pageable) ;

    PageImpl<ProductDTO> queryAll(final PageQueryCriteria criteria)
            throws IconQueryException;

}



 