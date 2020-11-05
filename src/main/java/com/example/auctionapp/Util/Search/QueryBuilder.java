package com.example.auctionapp.Util.Search;

import com.example.auctionapp.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private final EntityManager entityManager;

    private final CriteriaBuilder cb;

    private final CriteriaQuery<Product> query;

    private final Root<Product> resource;

    private List<Predicate> predicates;

    private Order order;

    private Integer pageNumber;

    private Integer pageSize;

    public QueryBuilder(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
        this.query = this.cb.createQuery(Product.class);
        this.resource = this.query.from(Product.class);
        this.predicates = new ArrayList<>();
    }

    public void withName(String searchName) {
        String pattern = "%" + searchName.toLowerCase() + "%";
        Predicate name = cb.like(cb.lower(resource.get("name")), pattern);
        predicates.add(name);
    }

    public void withSubcategory(Long subcategoryId) {
        Predicate subcategory = cb.equal(resource.get("subcategory").get("id"), subcategoryId);
        predicates.add(subcategory);
    }

    public void withColor(String color) {
        Predicate colorPredicate = cb.equal(resource.get("color"), color);
        predicates.add(colorPredicate);
    }

    public void withSize(String size) {
        Predicate sizePredicate = cb.equal(resource.get("size"), size);
        predicates.add(sizePredicate);
    }

    public void withPrice(Double minPrice, Double maxPrice) {
        Predicate price = cb.between(resource.get("price"), minPrice, maxPrice);
        predicates.add(price);
    }

    public void withOrderDefault() {
        Order order = cb.asc(resource.get("id"));
        this.order=order;
    }

    public void withOrderPrice() {
        Order order = cb.asc(resource.get("price"));
        this.order = order;
    }

    public void withOrderNewness() {
        Order order = cb.desc(resource.get("date_created"));
        this.order = order;
    }

    public void withPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void withAuctionNotEnded() {
        Predicate endDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get("auctionEndDate"),
                                                    LocalDateTime.now(ZoneOffset.UTC));
        predicates.add(endDate);
    }

    public TypedQuery<Product> getQuery() {

        this.query.select(resource)
                .where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(order);

        TypedQuery<Product> typedQuery = entityManager.createQuery(this.query);
        typedQuery.setFirstResult((pageNumber-1)*pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery;

    }
}
