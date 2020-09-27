package com.example.auctionapp.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBaseController<T> {

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(Long id);

    ResponseEntity<T> add(T resource);

    ResponseEntity<T> update(T resource);

    ResponseEntity<Void> deleteById(Long id);
}
