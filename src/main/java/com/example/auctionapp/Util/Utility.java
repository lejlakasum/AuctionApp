package com.example.auctionapp.Util;

import com.example.auctionapp.exception.NotFoundException;
import com.example.auctionapp.repository.BaseRepository;

public class Utility {

    public static <T> T findIfExist(BaseRepository<T> repository, Long id, String resourceName) {

        T resource = repository.findById(id);
        if(resource == null) {
            String message = resourceName + " with id " + id + " does not exist";
            throw new NotFoundException(message);
        }

        return resource;
    }
}
