package com.example.auctionapp.service;

import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService implements IBaseService<ProductDto> {

    private static final String RESOURCE_NAME = "Product";

    @Autowired
    ProductRepository repository;
    @Autowired
    BaseRepository<Subcategory> subcategoryRepository;
    @Autowired
    UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<ProductDto> getAll() {

        List<Product> products = repository.findAll();

        return mapProductListToDtoList(products);
    }


    public ProductDto getById(Long id) {

        Product product = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return mapProductToProductDto(product);
    }

    public List<ProductDto> getRelatedProducts(Long productId, Long subcategoryId) {

        List<Product> products = repository.findRelatedProducts(productId, subcategoryId);

        return mapProductListToDtoList(products);
    }

    public List<ProductDto> getFeatureProducts() {

        List<Product> products = repository.getFeatureProducts();

        return mapProductListToDtoList(products);
    }

    public List<ProductDto> getNewArrivals() {

        List<Product> products = repository.getNewArrivals();

        return mapProductListToDtoList(products);
    }

    public List<ProductDto> getLastChance() {

        List<Product> products = repository.getLastChanceProducts();

        return mapProductListToDtoList(products);
    }

    public List<ProductDto> getTopRated() {

        List<Product> products = repository.getTopRatedProducts();

        return mapProductListToDtoList(products);
    }

    public List<ProductDto> getByCategory(String categoryName, Boolean feature) {

        return mapProductListToDtoList(repository.getCllectionByCategory(categoryName, feature));
    }


    public ProductDto add(ProductDto resource) {

        Subcategory subcategory = RepositoryUtility.findIfExist(subcategoryRepository, resource.getSubcategoryId(), "Subcategory");
        User user = RepositoryUtility.findIfExist(userRepository, resource.getUserId(), "User");

        List<Image> images = resource.getImagesUrl().stream().map(
                url -> {return new Image(url);
                }
        ).collect(Collectors.toList());

        Product product = repository.create(new Product(resource.getName(),
                                                        resource.getDescription(),
                                                        resource.getPrice(),
                                                        subcategory,
                                                        resource.getAuctionStartDate(),
                                                        resource.getAuctionEndDate(),
                                                        images,
                                                        resource.getFeature(),
                                                        user));
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
                images,
                product.getFeature(),
                product.getId()
        );

    }

    private List<ProductDto> mapProductListToDtoList(List<Product> products) {
        return products.stream().map(
                product -> {return mapProductToProductDto(product);
                }
        ).collect(Collectors.toList());
    }
}
