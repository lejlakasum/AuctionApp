package com.example.auctionapp.repository;

import com.example.auctionapp.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CategoryRepository extends BaseRepository<Category> {

    @PersistenceContext
    EntityManager entityManager;

    public Category findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Category> q = cb.createQuery(Category.class);
        Root<Category> resource = q.from(Category.class);
        q.select(resource);
        Predicate predicateForName = cb.equal(resource.get("name"), name);
        q.where(predicateForName);

        Category result = entityManager.createQuery(q).getSingleResult();

        return result;
    }
}
