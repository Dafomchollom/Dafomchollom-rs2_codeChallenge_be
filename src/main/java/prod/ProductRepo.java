package prod;

import org.springframework.stereotype.Repository;
import repo.CommonRepo;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends CommonRepo<Product, Long> {
    //    Boolean existsByCorporateId(String accountId);
//
//    Boolean existsByCorporateIdAndIdIsNot(String accountId, Long id);
//
//    Optional<Company> findFirstByCorporateId(String accountId);
//    @Query("select r from prod_main_cat r")
//    List<ProductMainCategory> findByCategoryId2();

//    Optional<ProductMainCategory> findByCategoryId(String categoryId);
    List<Product> findAllByCategoryId(String accountId);
    Optional<Product> findById(Long id);
//    Optional<ProductMainCategory> findByMainCategory(String mainCategory);
}
