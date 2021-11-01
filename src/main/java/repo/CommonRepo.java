package repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Fortune on 4/5/2017.
 */
@NoRepositoryBean
public interface CommonRepo<T, ID extends Serializable> extends JpaRepository<T, ID>
{
    //   private List<ModifiedEntityTypeEntity> searchModidfiedEntity(){
    //       CriteriaBuilder cb = em.getCriteriaBuilder();
    //
    //   }
//    T create(T entity);
//    Page<T> findUsingPattern(String pattern, Pageable details);

}