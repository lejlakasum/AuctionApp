package com.example.auctionapp.controller;

import java.util.List;

public interface IBaseController<T> {

    List<T> getAll();

    T getById(Long id);

    T add(T resource);

    T update(T resource);

    void deleteById(Long id);
}
