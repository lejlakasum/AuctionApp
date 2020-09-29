package com.example.auctionapp.Util;

import com.example.auctionapp.exception.NotFoundException;
import com.example.auctionapp.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

    private static Logger logger = LoggerFactory.getLogger(Utility.class);

    public static <T> T findIfExist(BaseRepository<T> repository, Long id, String resourceName) {

        T resource = repository.findById(id);
        if(resource == null) {
            String message = resourceName + " with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        return resource;
    }
}
