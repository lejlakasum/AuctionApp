package com.example.auctionapp.repository;

import com.example.auctionapp.Util.Search.ProductSearchQueryFactory;
import com.example.auctionapp.dto.FilterDto.FilterPriceDto;
import com.example.auctionapp.dto.SearchRequest;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Rating;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


public class ProductRepository extends BaseRepository<Product> {

    public static final Integer MAX_RESULT = 3;
    public static final Integer MAX_RESULT_FEATURE = 4;
    public static final Integer MAX_RESULT_ARRIVALS = 8;
    public static final Integer MAX_RESULT_COLLECTIONS = 10;
    public static final Integer MAX_TOP_RATED = 5;

    public ProductRepository(Class<Product> resourceClass, EntityManager entityManager) {
        super(resourceClass, entityManager);
    }

    public List<Product> findRelatedProducts(Long productId, Long subcategoryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);
        Predicate predicateForSubcategory = cb.equal(resource.get("subcategory").get("id"), subcategoryId);
        Predicate predicateForProduct = cb.notEqual(resource.get("id"), productId);
        Predicate predicateForEndDate = getPredicateForEndDate(resource, cb);
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
        Predicate predicateForEndDate = getPredicateForEndDate(resource, cb);
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

        Predicate predicateForEndDate = getPredicateForEndDate(resource, cb);
        Predicate predicateForStartDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionStartDate"),
                                                                  LocalDateTime.now(ZoneOffset.UTC).minusHours(48));

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
                                                    LocalDateTime.now(ZoneOffset.UTC),
                                                    LocalDateTime.now(ZoneOffset.UTC).plusHours(24)
        );

        q.where(predicateForEndDate);
        q.orderBy(cb.asc(resource.get("auctionEndDate")));

        List<Product> result = entityManager.createQuery(q).setMaxResults(MAX_RESULT_ARRIVALS).getResultList();

        return result;
    }

    public List<Product> getCllectionByCategory(Long categoryId, Boolean feature) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);

        Predicate predicateForEndDate = getPredicateForEndDate(resource, cb);
        Predicate predicateForFeature = cb.equal(resource.get("feature"), feature);
        Predicate predicateForCategory = cb.equal(resource.get("subcategory").get("category").get("id"), categoryId);

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


        Predicate predicateForEndDate = getPredicateForEndDate(product, cb);

        List<Rating> users = entityManager.createQuery(ratingQuery).setMaxResults(MAX_TOP_RATED).getResultList();

        productQuery.select(product)
                .where(
                        cb.and(predicateForEndDate,
                                cb.in(product.get("user").get("id")).value(users))
                );

        List<Product> result = entityManager.createQuery(productQuery).setMaxResults(MAX_RESULT_ARRIVALS).getResultList();

        return result;

    }

    public Double getCollectionLowestPrice(String categoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> resource = q.from(Product.class);
        q.select(resource);

        Predicate predicateForEndDate = getPredicateForEndDate(resource, cb);
        Predicate predicateForFeature = cb.equal(resource.get("feature"), true);
        Predicate predicateForCategory = cb.like(resource.get("subcategory").get("category").get("name"), categoryName);

        q.where(
                cb.and(predicateForEndDate,
                predicateForFeature,
                predicateForCategory)
        );
        q.orderBy(cb.asc(resource.get("price")));

        List<Product> resultList = entityManager.createQuery(q).getResultList();
        Double lowestPrice = 0.;
        if(!resultList.isEmpty()) {
            lowestPrice = resultList.get(0).getPrice();
        }

        return lowestPrice;
    }

    public Long getProductsCountBySubcategory(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<Product> resource = q.from(Product.class);

        Predicate predicateForEndDate = getPredicateForEndDate(resource, cb);
        Predicate predicatedorSubcategory = cb.equal(resource.get("subcategory").get("id"), id);

        q.multiselect(cb.count(resource.get("id")))
                .where(
                        cb.and(predicateForEndDate, predicatedorSubcategory)
                );

        List<Tuple> result = entityManager.createQuery(q).getResultList();

        if(!result.isEmpty()) {
            return (Long) result.get(0).get(0);
        }

        return 0L;

    }

    public FilterPriceDto getPricesInfo() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<Product> resource = q.from(Product.class);

        Predicate predicateForEndDate = getPredicateForEndDate(resource, cb);

        q.multiselect(cb.min(resource.get("price")), cb.max(resource.get("price")), cb.avg(resource.get("price")))
                .where(predicateForEndDate);

        Tuple result = entityManager.createQuery(q).getResultList().get(0);

        Query pricesQuery = entityManager.createNativeQuery("SELECT count(id), round(cast(price as numeric), -1) FROM public.product GROUP BY price ORDER BY price");

        List<Tuple> pricesTuple = pricesQuery.getResultList();

        return new FilterPriceDto((Double) result.get(0),
                                  (Double) result.get(1),
                                  (Double) result.get(2),
                                  pricesTuple
        );
    }

    public List<Product> getSearchProducts(SearchRequest searchRequest) {

        TypedQuery query = ProductSearchQueryFactory.createQuery(searchRequest, entityManager);

        return query.getResultList();
    }

    private static Predicate getPredicateForEndDate(Root<Product> resource, CriteriaBuilder cb) {
        return cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionEndDate"), LocalDateTime.now(ZoneOffset.UTC));
    }

}
