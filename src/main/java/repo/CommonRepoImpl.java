package repo;


import category.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by ayoade_farooq@yahoo.com on 4/7/2017.
 */
@Transactional
public class CommonRepoImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonRepo<T, ID> {
    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommonRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }


    //    @Override
//    @Transactional
//    public void delete(ID id) {
//        try {
//            T t = getOne(id);
//            if (null != t) {
//                safeDelete(t);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //    @Override

//    @Override
//    public void deleteById(ID id) {
//        delete(id);
//    }




    //    @Override
    @Transactional
    public T findOneById(ID id) {
        logger.info("the id to fetch {}", id);
        Assert.notNull(id, "The given id is null!");
        try {
            return super.getOne(id);
        } catch (NoSuchElementException e) {
            return null;
        } catch (Exception e) {
            System.out.println("the exception caught " + e.getMessage());
            return null;
        }

    }


//    @Override
//    public void deleteInBatch(Iterable<T> entities) {
//        Assert.notNull(entities, "The given Iterable of entities can not be null!");
//
//        for (T entity : entities) {
//            safeDelete(entity);
//        }
//    }

//    @Override
//    @Transactional
//    public void deleteAll() {
//
//        for (T entity : this.findAll()) {
//            safeDelete(entity);
//        }
//    }

    @Override
    @Transactional
    public void deleteAllInBatch() {
//        super.deleteAllInBatch();
    }

//    @Override
//    public Page<T> findUsingPattern(String pattern, Pageable details)
//    {
//        String lpattern = pattern.toLowerCase();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<T> q = cb.createQuery(entityInformation.getJavaType());
//        Root<T> c = q.from(entityInformation.getJavaType());
//        Predicate[] predicates = null;
//        try {
//            predicates = new Predicate[getFields().size()];
//            int cnt = 0;
//            for (String field : getFields()) {
//                Predicate predicate = cb.like(cb.lower(c.get(field)), "%" + lpattern + "%");
//                predicates[cnt] = predicate;
//                cnt++;
//            }
//        }
//        catch (InstantiationException | IllegalAccessException e)
//        {
//            return new PageImpl<>(new ArrayList<>());
//        }
//
//        CriteriaQuery<T> baseQuery = null;
//        CriteriaQuery<Long> qc = cb.createQuery(Long.class);
//        CriteriaQuery<Long> countQuery = null;
//        logger.debug("the predicates "+ Arrays.toString(predicates));
//        if(predicates.length > 0)
//        {
//            Predicate or = cb.or (predicates);
//            baseQuery = q.select(c).where(or);
//            countQuery = qc.select(cb.count(qc.from(entityInformation.getJavaType()))).where(or);
//        }
//        else
//        {
//            baseQuery = q.select(c);
//            countQuery = qc.select(cb.count(qc.from(entityInformation.getJavaType())));
//        }
//
//        TypedQuery<T> query = em.createQuery(baseQuery);
//        Long count = em.createQuery(countQuery).getSingleResult();
//        query.setFirstResult(Math.toIntExact(details.getOffset()));
//        query.setMaxResults(details.getPageSize());
//        List<T> list = query.getResultList();
//        return new PageImpl<>(list, details, count);
//    }


    private List<String> getFields() throws InstantiationException, IllegalAccessException {
        Class<T> type = entityInformation.getJavaType();
        BaseEntity en = type.newInstance();
        return en.getDefaultSearchFields();

    }

    //   private List<ModifiedEntityTypeEntity> searchModidfiedEntity(){
//       CriteriaBuilder cb = em.getCriteriaBuilder();
//
//   }
//    @Override
//    public T create(T entity) {
//        Assert.notNull(entity, "The entity must not be null!");
//
////    if (StringUtils.hasText(ActiveAuditor.getInitiator())) {
////        entity.setCreatedBy(ActiveAuditor.getInitiator());
////    } else {
////        entity.setCreatedBy(ActiveAuditor.getCurrentAuditorStr());
////    }
//
////    entity.setApprovedBy(ActiveAuditor.getCurrentAuditorStr());
//
//        entity.setCreatedDate(LocalDateTime.now());
////        entity.setCreatedByIp(activeAuditor.getCurrentAuditorIp());
////    entity.setRequestId(ActiveAuditor.getRequestId());
//
//        entityManager.persist(entity);
//
////        if (applicationEventPublisher != null) {
////            applicationEventPublisher.publishEvent(new OnEntityCreatedEvent<>(entity, this.getClass()));
////        }
//
//        return entity;
//    }
}