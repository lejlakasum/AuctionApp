package com.example.auctionapp.service;

import com.example.auctionapp.Util.Utility;
import com.example.auctionapp.dto.ImageDto;
import com.example.auctionapp.dto.SubcategoryDto;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ImageService implements IBaseService<ImageDto> {

    private static final String RESOURCE_NAME = "Image";

    private BaseRepository<Image> repository;
    private BaseRepository<Product> productRepository;

    private static Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Autowired
    public ImageService(BaseRepository<Image> repository,
                              BaseRepository<Product> productRepository) {
        this.repository = repository;
        this.repository.setResourceClass(Image.class);
        this.productRepository =productRepository;
        this.productRepository.setResourceClass(Product.class);
    }


    public List<ImageDto> getAll() {

        List<Image> images = repository.findAll();
        List<ImageDto> imageDtos = new ArrayList<>();

        for (Image image:images) {
            imageDtos.add(mapImageToImageDto(image));
        }

        return  imageDtos;
    }


    public ImageDto getById(Long id) {

        Image image = Utility.findIfExist(repository, id, RESOURCE_NAME);

        return mapImageToImageDto(image);
    }


    public ImageDto add(ImageDto resource) {

        Product product = Utility.findIfExist(productRepository, resource.getProductId(), "Product");

        Image image = repository.create(new Image(resource.getUrl(), product));
        logger.info("Image with id " + image.getId() + " created");
        return mapImageToImageDto(image);
    }


    public ImageDto update(ImageDto resource) {

        Image resourceToUpdate = Utility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Product product = Utility.findIfExist(productRepository, resource.getProductId(), "Product");

        resourceToUpdate.setUrl(resource.getUrl());
        resourceToUpdate.setProduct(product);

        Image image = repository.update(resourceToUpdate);
        logger.info("Image with id " + image.getId() + " updated");

        return mapImageToImageDto(image);
    }


    public void deleteById(Long id) {

        Utility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Image with id " + id + " deleted");
    }

    private ImageDto mapImageToImageDto(Image image) {
        return new ImageDto(image.getId(),
                image.getDateCreated(),
                image.getLastModifiedDate(),
                image.getUrl(),
                image.getProduct().getId());
    }
}
