package com.example.auctionapp.service;

import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService implements IBaseService<ProductDto> {

    private static final String RESOURCE_NAME = "Product";

    @Autowired
    ProductRepository repository;
    BaseRepository<Subcategory> subcategoryRepository;

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(BaseRepository<Subcategory> subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.subcategoryRepository.setResourceClass(Subcategory.class);
    }

    public List<ProductDto> getAll() {
        List<Product> products = repository.findAll();
        List<ProductDto> productDtos = products.stream().map(
                product -> {return mapProductToProductDto(product);
                }
        ).collect(Collectors.toList());

        return productDtos;
    }


    public ProductDto getById(Long id) {

        Product product = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return mapProductToProductDto(product);
    }

    public List<ProductDto> getRelatedProducts(Long productId, Long subcategoryId) {

        List<Product> products = repository.findRelatedProducts(productId, subcategoryId);
        List<ProductDto> productDtos = products.stream().map(
                product -> {return mapProductToProductDto(product);
                }
        ).collect(Collectors.toList());

        return productDtos;
    }


    public ProductDto add(ProductDto resource) {

        Subcategory subcategory = RepositoryUtility.findIfExist(subcategoryRepository, resource.getSubcategoryId(), "Subcategory");

        List<Image> images = new ArrayList<>();
        for (String url:resource.getImagesUrl()) {
            images.add(new Image(url));
        }

        Product product = repository.create(new Product(resource.getName(),
                                                        resource.getDescription(),
                                                        resource.getPrice(),
                                                        subcategory,
                                                        resource.getAuctionStartDate(),
                                                        resource.getAuctionEndDate(),
                                                        images));
        logger.info("Product with id " + product.getId() + " created");
        return mapProductToProductDto(product);
    }


    public ProductDto update(ProductDto resource) {

        Product resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Subcategory subcategory = RepositoryUtility.findIfExist(subcategoryRepository, resource.getSubcategoryId(), "Subcategory");

        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setDescription(resource.getDescription());
        resourceToUpdate.setPrice(resource.getPrice());
        resourceToUpdate.setAuctionStartDate(resource.getAuctionStartDate());
        resourceToUpdate.setAuctionEndDate(resource.getAuctionEndDate());
        resourceToUpdate.setSubcategory(subcategory);

        Product product = repository.update(resourceToUpdate);
        logger.info("Product with id " + product.getId() + " updated");

        return mapProductToProductDto(product);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Product with id " + id + " deleted");
    }

    private ProductDto mapProductToProductDto(Product product) {

        List<String> images = product.getImages().stream().map(
                image -> {return image.getUrl();
                }
        ).collect(Collectors.toList());

        return new ProductDto(product.getId(),
                product.getDateCreated(),
                product.getLastModifiedDate(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSubcategory().getId(),
                product.getAuctionStartDate(),
                product.getAuctionEndDate(),
                images
        );

    }
}
