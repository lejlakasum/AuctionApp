package com.example.auctionapp.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BaseRepository<T> implements IBaseRepository<T> {

    private final Class<T> resourceClass;

    protected final EntityManager entityManager;

    public BaseRepository(Class<T> resourceClass, EntityManager entityManager) {
        this.resourceClass = resourceClass;
        this.entityManager = entityManager;
    }

    public T findById(Long id){
        return entityManager.find(resourceClass, id);
    }

    public List<T> findAll(){
        CriteriaBuilder cb = getCriteriaBuilder();

        CriteriaQuery<T> q = cb.createQuery(resourceClass);
        Root<T> resource = q.from(resourceClass);
        q.select(resource);

        TypedQuery<T> query = entityManager.createQuery(q);
        return query.getResultList();
    }

    @Valid
    public T create(T resource){
        entityManager.persist( resource );
        return resource;
    }

    @Valid
    public T update(T resource){
        return entityManager.merge(resource);
    }

    public void delete(T resource){
        entityManager.remove(resource);
    }

    public void deleteById(Long resourceId){
        T resource = findById(resourceId);
        delete(resource);
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }
}
