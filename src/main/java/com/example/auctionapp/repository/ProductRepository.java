package com.example.auctionapp.repository;

import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Rating;
import com.example.auctionapp.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProductRepository extends BaseRepository<Product> {

    @PersistenceContext
    EntityManager entityManager;

    public static final Integer MAX_RESULT = 3;
    public static final Integer MAX_RESULT_FEATURE = 4;
    public static final Integer MAX_RESULT_ARRIVALS = 8;
    public static final Integer MAX_RESULT_COLLECTIONS = 10;
    public static final Integer MAX_TOP_RATED = 5;

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

    public List<Product> getLastChanceProducts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);
        Predicate predicateForEndDate = cb.between(resource.<LocalDateTime>get("auctionEndDate"),
                                                    LocalDateTime.now(),
                                                    LocalDateTime.now().plusHours(24)
        );

        q.where(predicateForEndDate);
        q.orderBy(cb.asc(resource.get("auctionEndDate")));

        List<Product> result = entityManager.createQuery(q).setMaxResults(MAX_RESULT_ARRIVALS).getResultList();

        return result;
    }

    public List<Product> getCllectionByCategory(String categoryName, Boolean feature) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);

        Predicate predicateForEndDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now());
        Predicate predicateForFeature = cb.equal(resource.get("feature"), feature);
        Predicate predicateForCategory = cb.like(resource.get("subcategory").get("category").get("name"), categoryName);

        q.where(
                cb.and(predicateForEndDate,
                        predicateForCategory)
        );

        if(feature) {
            q.where(
                    cb.and(predicateForEndDate,
                            predicateForFeature,
                            predicateForCategory)
            );
        }
        else {
            q.where(
                    cb.and(predicateForEndDate,
                            predicateForCategory)
            );
        }

        List<Product> result = entityManager.createQuery(q).setMaxResults(MAX_RESULT_COLLECTIONS).getResultList();

        return result;
    }

    public List<Product> getTopRatedProducts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> productQuery = cb.createQuery(Product.class);
        Root<Product> product = productQuery.from(Product.class);

        CriteriaQuery<Rating> ratingQuery = cb.createQuery(Rating.class);
        Root<Rating> rating = ratingQuery.from(Rating.class);

        ratingQuery.select(rating.get("user").get("id"))
                .groupBy(rating.get("user").get("id"))
                .orderBy(cb.desc(cb.avg(rating.get("grade"))));


        Predicate predicateForEndDate = cb.greaterThanOrEqualTo(product.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now());

        List<Rating> users = entityManager.createQuery(ratingQuery).setMaxResults(MAX_TOP_RATED).getResultList();

        productQuery.select(product)
                .where(
                        cb.and(predicateForEndDate,
                                cb.in(product.get("user").get("id")).value(users))
                );

        List<Product> result = entityManager.createQuery(productQuery).setMaxResults(MAX_RESULT_ARRIVALS).getResultList();

        return result;

    }

}
