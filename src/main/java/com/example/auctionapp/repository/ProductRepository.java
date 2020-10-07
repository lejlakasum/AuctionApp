package com.example.auctionapp.repository;

import com.example.auctionapp.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;


public class ProductRepository extends BaseRepository<Product> {

    @PersistenceContext
    EntityManager entityManager;

    public static final Integer MAX_RESULT = 3;
    public static final Integer MAX_RESULT_FEATURE = 4;
    public static final Integer MAX_RESULT_ARRIVALS = 6;

    public List<Product> findRelatedProducts(Long productId, Long subcategoryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);
        Predicate predicateForSubcategory = cb.equal(resource.get("subcategory").get("id"), subcategoryId);
        Predicate predicateForProduct = cb.notEqual(resource.get("id"), productId);
        Predicate predicateForEndDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now());
        q.where(
                cb.and(
                        predicateForProduct,
                        predicateForSubcategory,
                        predicateForEndDate
                )
        );

        List<Product> result = entityManager.createQuery(q).setMaxResults(MAX_RESULT).getResultList();

        return result;
    }

    public List<Product> getFeatureProducts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);

        Predicate predicateForFeature = cb.equal(resource.get("feature"), true);
        Predicate predicateForEndDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now());
        q.where(
                cb.and(predicateForFeature,
                        predicateForEndDate)
        );

        List<Product> result = entityManager.createQuery(q).setMaxResults(MAX_RESULT_FEATURE).getResultList();

        return result;
    }

    public List<Product> getNewArrivals() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);

        Predicate predicateForEndDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now());
        Predicate predicateForStartDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionStartDate"),
                                                                  LocalDateTime.now().minusHours(48));

        q.where(
                cb.and(predicateForStartDate,
                        predicateForEndDate)
        );
        q.orderBy(cb.desc(resource.get("auctionStartDate")));

        List<Product> result = entityManager.createQuery(q).setMaxResults(MAX_RESULT_ARRIVALS).getResultList();

        return result;
    }

}
