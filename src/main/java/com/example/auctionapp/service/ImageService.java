package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.ImageDto;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageService implements IBaseService<ImageDto> {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private static final String RESOURCE_NAME = "Image";

    private final BaseRepository<Image> repository;

    @Autowired
    public ImageService(BaseRepository<Image> repository) {
        this.repository = repository;
    }

    public List<ImageDto> getAll() {

        List<Image> images = repository.findAll();
        List<ImageDto> imageDtos = images.stream().map(
                image -> { return new ImageDto(
                        image.getId(),
                        image.getDateCreated(),
                        image.getLastModifiedDate(),
                        image.getUrl());
                }
        ).collect(Collectors.toList());

        return  imageDtos;
    }


    public ImageDto getById(Long id) {

        Image image = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapImageToImageDto(image);
    }


    public ImageDto add(ImageDto resource) {

        Image image = repository.create(new Image(resource.getUrl()));
        logger.info("Image with id " + image.getId() + " created");
        return MappingUtility.mapImageToImageDto(image);
    }


    public ImageDto update(ImageDto resource) {

        Image resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        resourceToUpdate.setUrl(resource.getUrl());

        Image image = repository.update(resourceToUpdate);
        logger.info("Image with id " + image.getId() + " updated");

        return MappingUtility.mapImageToImageDto(image);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Image with id " + id + " deleted");
    }
}
