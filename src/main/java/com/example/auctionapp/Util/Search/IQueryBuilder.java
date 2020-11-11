package com.example.auctionapp.Util.Search;

import javax.persistence.TypedQuery;

public interface IQueryBuilder<T> {

    void withOrderDefault();

    void withOrderNewness();

    void withPageNumber(Integer pageNumber);

    void withPageSize(Integer pageSize);

    TypedQuery<T> getQuery();

}
