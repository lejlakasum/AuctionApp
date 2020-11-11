package com.example.auctionapp.Util.Search;

import com.example.auctionapp.dto.SearchRequest;
import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.SizeEnum;
import com.example.auctionapp.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProductSearchQueryFactory {

    public static TypedQuery createQuery(SearchRequest searchRequest, EntityManager entityManager) {
        ProductQueryBuilder queryBuilder = new ProductQueryBuilder(Product.class,entityManager);

        if(!searchRequest.getName().isEmpty()) {
            queryBuilder.withName(searchRequest.getName());
        }

        if(searchRequest.getSubcategoryId() > 0) {
            queryBuilder.withSubcategory(searchRequest.getSubcategoryId());
        }

        if(!searchRequest.getColor().isEmpty()) {
            queryBuilder.withColor(ColorEnum.fromValue(searchRequest.getColor()));
        }

        if(!searchRequest.getSize().isEmpty()) {
            queryBuilder.withSize(SizeEnum.fromValue(searchRequest.getSize()));
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

        queryBuilder.withPageSize(searchRequest.getPageSize());
        queryBuilder.withPageNumber(searchRequest.getPageNumber());
        queryBuilder.withAuctionNotEnded();

        return queryBuilder.getQuery();
    }
}
