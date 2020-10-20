package com.example.auctionapp.repository;

import com.example.auctionapp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class UserRepository extends BaseRepository<User> {

    public UserRepository(Class<User> resourceClass, EntityManager entityManager) {
        super(resourceClass, entityManager);
    }

    public User findByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> resource = q.from(User.class);
        q.select(resource);
        Predicate predicateForEmail = cb.equal(resource.get("email"), email);
        q.where(predicateForEmail);

        User result = entityManager.createQuery(q).getSingleResult();

        return result;
    }
}
