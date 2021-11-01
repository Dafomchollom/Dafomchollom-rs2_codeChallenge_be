package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Repository
public class IdentifierDAO {
    @Autowired
    private EntityManager em;


    public String getIdentifier() {
//        String inital
        BigDecimal id = (BigDecimal)this.em.createNativeQuery("select beaver.product_ID"+ ".nextval from dual").getSingleResult();
        return id.toString();
    }

}
