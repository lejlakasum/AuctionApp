package com.example.auctionapp.service;

import java.util.List;

public interface IBaseService <T> {

    List<T> getAll();

    T getById(Long id);

    T add(T resource);

    T update(T resource);

    void deleteById(Long id);

}
