package com.example.auctionapp.Util.Search;

import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.SizeEnum;
import com.example.auctionapp.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ProductQueryBuilder extends QueryBuilder<Product> {

    private static final String NAME = "name";
    private static final String SUBCATEGORY = "subcategory";
    private static final String ID = "id";
    private static final String COLOR = "color";
    private static final String SIZE = "size";
    private static final String PRICE = "price";
    private static final String END_DATE = "auctionEndDate";

    public ProductQueryBuilder(Class<Product> resourceClass, EntityManager entityManager) {
        super(resourceClass, entityManager);
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

    public void withOrderPrice() {
        Order order = cb.asc(resource.get(PRICE));
        this.order = order;
    }

    public void withAuctionNotEnded() {
        Predicate endDate = cb.greaterThanOrEqualTo(resource.<LocalDateTime>get(END_DATE),
                LocalDateTime.now(ZoneOffset.UTC));
        predicates.add(endDate);
    }
}
