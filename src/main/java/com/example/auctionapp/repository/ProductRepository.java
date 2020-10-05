package com.example.auctionapp.repository;

import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductRepository extends BaseRepository<Product> {

    @PersistenceContext
    EntityManager entityManager;

    public List<Product> findRelatedProducts(Long productId, Long subcategoryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);
        Predicate predicateForSubcategory = cb.equal(resource.get("subcategory").get("id"), subcategoryId);
        Predicate predicateForProduct = cb.notEqual(resource.get("id"), productId);
        q.where(
                cb.and(
                        predicateForProduct,
                        predicateForSubcategory
                )
        );

        List<Product> result = entityManager.createQuery(q).getResultList();

        return result;
    }
}
