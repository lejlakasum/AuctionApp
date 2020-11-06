package com.example.auctionapp.Util.Search;

import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.SizeEnum;
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

    private static final String NAME = "name";
    private static final String SUBCATEGORY = "subcategory";
    private static final String ID = "id";
    private static final String COLOR = "color";
    private static final String SIZE = "size";
    private static final String PRICE = "price";
    private static final String DATE_CREATED = "dateCreated";
    private static final String END_DATE = "auctionEndDate";

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
        Predicate name = cb.like(cb.lower(resource.get(NAME)), pattern);
        predicates.add(name);
    }

    public void withSubcategory(Long subcategoryId) {
        Predicate subcategory = cb.equal(resource.get(SUBCATEGORY).get(ID), subcategoryId);
        predicates.add(subcategory);
    }

    public void withColor(ColorEnum color) {
        Predicate colorPredicate = cb.equal(resource.get(COLOR), color);
        predicates.add(colorPredicate);
    }

    public void withSize(SizeEnum size) {
        Predicate sizePredicate = cb.equal(resource.get(SIZE), size);
        predicates.add(sizePredicate);
    }

    public void withPrice(Double minPrice, Double maxPrice) {
        Predicate price = cb.between(resource.get(PRICE), minPrice, maxPrice);
        predicates.add(price);
    }

    public void withOrderDefault() {
        Order order = cb.asc(resource.get(ID));
        this.order=order;
    }

    public void withOrderPrice() {
        Order order = cb.asc(resource.get(PRICE));
        this.order = order;
    }

    public void withOrderNewness() {
        Order order = cb.desc(resource.get(DATE_CREATED));
        this.order = order;
    }

    public void withPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void withAuctionNotEnded() {
        Predicate endDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get(END_DATE),
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
