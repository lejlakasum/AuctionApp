package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.Util.TimeUtility;
import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.SizeEnum;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.UserAccount;
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

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private static final String RESOURCE_NAME = "Product";

    private final ProductRepository repository;
    private final BaseRepository<Subcategory> subcategoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository repository,
                          BaseRepository<Subcategory> subcategoryRepository,
                          UserRepository userRepository) {
        this.repository = repository;
        this.subcategoryRepository = subcategoryRepository;
        this.userRepository = userRepository;
    }

    public List<ProductDto> getAll() {

        List<Product> products = repository.findAll();

        return MappingUtility.mapProductListToDtoList(products);
    }


    public ProductDto getById(Long id) {

        Product product = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapProductToProductDto(product);
    }

    public List<ProductDto> getRelatedProducts(Long productId, Long subcategoryId) {

        List<Product> products = repository.findRelatedProducts(productId, subcategoryId);

        return MappingUtility.mapProductListToDtoList(products);
    }

    public List<ProductDto> getFeatureProducts() {

        List<Product> products = repository.getFeatureProducts();

        return MappingUtility.mapProductListToDtoList(products);
    }

    public List<ProductDto> getNewArrivals() {

        List<Product> products = repository.getNewArrivals();

        return MappingUtility.mapProductListToDtoList(products);
    }

    public List<ProductDto> getLastChance() {

        List<Product> products = repository.getLastChanceProducts();

        return MappingUtility.mapProductListToDtoList(products);
    }

    public List<ProductDto> getTopRated() {

        List<Product> products = repository.getTopRatedProducts();

        return MappingUtility.mapProductListToDtoList(products);
    }

    public List<ProductDto> getByCategory(Long categoryId, Boolean feature) {

        return MappingUtility.mapProductListToDtoList(repository.getCllectionByCategory(categoryId, feature));
    }


    public ProductDto add(ProductDto resource) {

        Subcategory subcategory = RepositoryUtility.findIfExist(subcategoryRepository, resource.getSubcategoryId(), "Subcategory");
        UserAccount userAccount = RepositoryUtility.findIfExist(userRepository, resource.getUserId(), "User");

        List<Image> images = resource.getImagesUrl().stream().map(
                url -> {return new Image(url);
                }
        ).collect(Collectors.toList());

        Product product = repository.create(new Product(
                                            resource.getName(),
                                            resource.getDescription(),
                                            resource.getPrice(),
                                            subcategory,
                                            TimeUtility.timestampToLocalDateTime(resource.getAuctionStartDate()),
                                            TimeUtility.timestampToLocalDateTime(resource.getAuctionEndDate()),
                                            images,
                                            resource.getFeature(),
                userAccount,
                                            ColorEnum.fromValue(resource.getColor()),
                                            SizeEnum.fromValue(resource.getSize())));
        logger.info("Product with id " + product.getId() + " created");
        return MappingUtility.mapProductToProductDto(product);
    }


    public ProductDto update(ProductDto resource) {

        Product resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Subcategory subcategory = RepositoryUtility.findIfExist(subcategoryRepository, resource.getSubcategoryId(), "Subcategory");

        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setDescription(resource.getDescription());
        resourceToUpdate.setPrice(resource.getPrice());
        resourceToUpdate.setAuctionStartDate(TimeUtility.timestampToLocalDateTime(resource.getAuctionStartDate()));
        resourceToUpdate.setAuctionEndDate(TimeUtility.timestampToLocalDateTime(resource.getAuctionEndDate()));
        resourceToUpdate.setSubcategory(subcategory);
        resourceToUpdate.setColor(ColorEnum.fromValue(resource.getColor()));
        resourceToUpdate.setSize(SizeEnum.fromValue(resource.getSize()));

        Product product = repository.update(resourceToUpdate);
        logger.info("Product with id " + product.getId() + " updated");

        return MappingUtility.mapProductToProductDto(product);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Product with id " + id + " deleted");
    }
}
