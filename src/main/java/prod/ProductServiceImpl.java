package prod;

import domain.PageQueryCriteria;
import exception.EntityNotFoundException;
import exception.FieldValidationException;
import exception.IconQueryException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prod.dto.AddProductDTO;
import prod.dto.ProductDTO;
import prod.dto.UpdateProductDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo entityRepo;
    private final ProductValidator entityValidator;
    private final ModelMapper modelMapper;

    @Override
    public ProductDTO createOne(AddProductDTO dto) throws FieldValidationException, EntityNotFoundException {
        ProductDTO rsp = null;


        entityValidator.validateInsert(dto);

        Product entity = modelMapper.map(dto, Product.class);
        entityRepo.save(entity);

        rsp = (modelMapper.map(entity, ProductDTO.class));


        return rsp;

    }

    @Override
    public List<ProductDTO> createBatch(@NotEmpty List<AddProductDTO> dtos, Long companyId) throws FieldValidationException, EntityNotFoundException {
        entityValidator.validateBatchInsert(dtos, companyId);
        List<ProductDTO> rsp = new ArrayList<>();
        for (AddProductDTO dto : dtos) {
            Product relatedParty = modelMapper.map(dto, Product.class);
            relatedParty.setCategoryId(companyId);
            entityRepo.save(relatedParty);
            rsp.add(modelMapper.map(relatedParty, ProductDTO.class));
        }
        return rsp;
    }

    @Override
    public ProductDTO update(Long id, UpdateProductDTO dto) throws FieldValidationException, EntityNotFoundException {
        return null;
    }

    @Override
    public Boolean deleteById(@Positive Long id) throws FieldValidationException, EntityNotFoundException {
        return null;
    }

    @Override
    public ProductDTO findById(@Positive Long id) throws IconQueryException {
        final Optional<Product> product = entityRepo.findById(id);
        if(product.isPresent()){
           return modelMapper.map(product.get(), ProductDTO.class);
        }
        return new ProductDTO();
    }

    @Override
    public List<ProductDTO> findByCategoryId(@NotBlank String companyId) {
        List<ProductDTO> rsp = new ArrayList<>();

        List<Product> rows = entityRepo.findAllByCategoryId(companyId);
        List<ProductDTO> dtoRows = null;

        if (!rows.isEmpty()) {
            dtoRows = new ArrayList<>(rows.parallelStream()
                    .map(row -> modelMapper.map(row, ProductDTO.class))
                    .collect(Collectors.toList()));
            rsp = (dtoRows);

        }


        return rsp;
    }

    @Override
    public Page<ProductDTO> listAll(Pageable pageable)  {

        return null;
    }

    @Override
    public PageImpl<ProductDTO> queryAll(PageQueryCriteria criteria) throws IconQueryException {
        return null;
    }
}
