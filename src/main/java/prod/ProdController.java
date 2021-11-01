package prod;

import domain.ApiResponseBase;
import exception.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prod.dto.AddProductDTO;
import prod.dto.ProductDTO;
import prod.dto.UpdateProductDTO;

import javax.validation.constraints.Positive;
import java.util.List;

/***
 * @author : Ozioma Ikenna & Dada Oluwashina
 * @since : 08/05/2019
 */


@RestController
@RequestMapping("/product")
@Api(description = "Product endpoint.")
public class ProdController
{

    @Autowired
    private ProductService documentService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponseBase<ProductDTO>> createOne(@RequestBody final AddProductDTO dto) throws FieldValidationException, EntityNotFoundException, IconException
    {
        ApiResponseBase<ProductDTO> rsp = new ApiResponseBase<>();
        HttpStatus status = HttpStatus.OK;
            rsp.setResponse(documentService.createOne(dto));
        return new ResponseEntity<>(rsp, status);
    }


    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponseBase<ProductDTO>> update(@Positive @PathVariable(value = "id", required = true) Long id, @RequestBody final UpdateProductDTO dto)throws FieldValidationException, IconQueryException,EntityNotFoundException, ProcessException
    {
        ApiResponseBase<ProductDTO> rsp = new ApiResponseBase<>();
        HttpStatus status = HttpStatus.OK;

            rsp.setResponse(documentService.update(id,dto));
            rsp.setSuccessMessage("Document updated successfully");


        return new ResponseEntity<>(rsp, status);
    }

//    @DeleteMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity<ApiResponseBase<Boolean>> remove(@PathVariable @Positive final Long id)
//    {
//        ApiResponseBase<Boolean> rsp = new ApiResponseBase<>();
//        HttpStatus status = HttpStatus.OK;
//
//        try
//        {
//
//            rsp.setResponse(documentService.remove(id));
//
//        }
//        catch (Exception ex)
//        {
//
//            Tuple2<ApiResponseBase<Boolean>, HttpStatus> tuple = ErrorUtil.handleIconException(ex);
//            rsp = tuple.getFirst();
//            status = tuple.getSecond();
//
//        }
//
//
//        return new ResponseEntity<>(rsp, status);
//    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponseBase<ProductDTO>> findById(
            @PathVariable @Positive final Long id) throws IconQueryException
    {
        ApiResponseBase<ProductDTO> rsp = new ApiResponseBase<>();
        HttpStatus status = HttpStatus.OK;


            rsp.setResponse(documentService.findById(id));



        return new ResponseEntity<>(rsp, status);
    }
    @GetMapping("account-id/{categoryId}")
    @ResponseBody
    public ResponseEntity<ApiResponseBase<List<ProductDTO>>> findById(
            @PathVariable final String categoryId) throws IconQueryException
    {
        ApiResponseBase<List<ProductDTO>> rsp = new ApiResponseBase<>();
        HttpStatus status = HttpStatus.OK;


            List<ProductDTO> dto = documentService.findByCategoryId(categoryId);
            rsp.setResponse(dto);


        return new ResponseEntity<>(rsp, status);
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<ApiResponseBase<Page<ProductDTO>>> listAll(Pageable pageable) throws IconQueryException
    {
        ApiResponseBase<Page<ProductDTO>> rsp = new ApiResponseBase<>();
        HttpStatus status = HttpStatus.OK;


            rsp.setResponse(documentService.listAll(pageable));


        return new ResponseEntity<>(rsp, status);
    }





}

