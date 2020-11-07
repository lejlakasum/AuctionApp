package com.example.auctionapp.Util.Search;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder<T> implements IQueryBuilder<T> {

    private static final String ID = "id";
    private static final String DATE_CREATED = "dateCreated";

    protected final EntityManager entityManager;

    protected final CriteriaBuilder cb;

    protected final CriteriaQuery<T> query;

    protected final Root<T> resource;

    protected List<Predicate> predicates;

    protected Order order;

    protected Integer pageNumber;

    protected Integer pageSize;

    public QueryBuilder(Class<T> resourceClass, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
        this.query = this.cb.createQuery(resourceClass);
        this.resource = this.query.from(resourceClass);
        this.predicates = new ArrayList<>();
    }

    public void withOrderDefault() {
        Order order = cb.asc(resource.get(ID));
        this.order=order;
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

    public TypedQuery<T> getQuery() {

        this.query.select(resource)
                .where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(order);

        TypedQuery<T> typedQuery = entityManager.createQuery(this.query);
        typedQuery.setFirstResult((pageNumber-1)*pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery;

    }

}
