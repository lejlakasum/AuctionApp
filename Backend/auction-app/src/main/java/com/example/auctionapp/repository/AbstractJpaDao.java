package com.example.auctionapp.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractJpaDao<T> {

    private Class<T> resourceClass;

    @PersistenceContext
    EntityManager entityManager;

    public void setResourceClass( Class< T > classToSet ) {
        this.resourceClass = classToSet;
    }

    public T findById(Long id){
        return entityManager.find(resourceClass, id);
    }

    public List<T> findAll(){
        return entityManager.createQuery("from " + resourceClass.getName())
                .getResultList();
    }

    public T create(T resource){
        entityManager.persist( resource );
        return resource;
    }

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
}
