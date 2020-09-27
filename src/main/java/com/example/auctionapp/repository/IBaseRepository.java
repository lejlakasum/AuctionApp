package com.example.auctionapp.repository;

import java.util.List;

public interface IBaseRepository<T> {

    T findById(final Long id);

    List<T> findAll();

    T create(final T resource);

    T update(final T resource);

    void delete (final T resource);

    void deleteById(final Long id);

}
