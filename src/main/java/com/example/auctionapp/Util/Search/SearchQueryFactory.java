package com.example.auctionapp.Util.Search;

import com.example.auctionapp.dto.SearchRequest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SearchQueryFactory {

    private static final Integer PAGE_SIZE = 9;

    public static TypedQuery createQuery(SearchRequest searchRequest, EntityManager entityManager) {
        QueryBuilder queryBuilder = new QueryBuilder(entityManager);

        if(!searchRequest.getName().isEmpty()) {
            queryBuilder.withName(searchRequest.getName());
        }

        if(searchRequest.getSubcategoryId() > 0) {
            queryBuilder.withSubcategory(searchRequest.getSubcategoryId());
        }

        if(!searchRequest.getColor().isEmpty()) {
            queryBuilder.withColor(searchRequest.getColor());
        }

        if(!searchRequest.getSize().isEmpty()) {
            queryBuilder.withSize(searchRequest.getSize());
        }

        if(searchRequest.getMinPrice() >= 0 && searchRequest.getMaxPrice() >= 0) {
            queryBuilder.withPrice(searchRequest.getMinPrice(), searchRequest.getMaxPrice());
        }

        switch(searchRequest.getOrder()) {
            case "default":
                queryBuilder.withOrderDefault();
                break;
            case "price":
                queryBuilder.withOrderPrice();
                break;
            case "newness":
                queryBuilder.withOrderNewness();
                break;
        }

        queryBuilder.withPageSize(PAGE_SIZE);
        queryBuilder.withPageNumber(searchRequest.getPageNumber());
        queryBuilder.withAuctionNotEnded();

        return queryBuilder.getQuery();
    }
}
