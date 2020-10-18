package com.example.auctionapp.service;

import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.CategoryDto;
import com.example.auctionapp.dto.CollectionDto;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService implements IBaseService<CategoryDto> {

    private static final String RESOURCE_NAME = "Category";
    public static final List<String> featureCategories = Arrays.asList("Fashion", "Shoes", "Electronics");

    @Autowired
    CategoryRepository repository;

    @Autowired
    ProductRepository productRepository;

    private static Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public List<CategoryDto> getAll() {

        List<Category> categories = repository.findAll();

        List<CategoryDto> categoryDtos = categories.stream().map(
                category -> { return mapCategoryToCategoryDto(category);
                }
        ).collect(Collectors.toList());

        return  categoryDtos;
    }


    public CategoryDto getById(Long id) {

        Category category = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return mapCategoryToCategoryDto(category);
    }

    public List<CollectionDto> findFeatureCategories() {
        List<CollectionDto> collectionDtos = new ArrayList<>();

        collectionDtos.add(new CollectionDto(
                        mapCategoryToCategoryDto(repository.findByName(featureCategories.get(0))),
                        productRepository.getCollectionLowestPrice(featureCategories.get(0))));

        collectionDtos.add(new CollectionDto(
                mapCategoryToCategoryDto(repository.findByName(featureCategories.get(1))),
                productRepository.getCollectionLowestPrice(featureCategories.get(1))));

        collectionDtos.add(new CollectionDto(
                mapCategoryToCategoryDto(repository.findByName(featureCategories.get(2))),
                productRepository.getCollectionLowestPrice(featureCategories.get(2))));

        return collectionDtos;
    }

    public CategoryDto add(CategoryDto resource) {
        Category category = repository.create(new Category(
                                                        resource.getName(),
                                                        new Image(resource.getImageUrl()))
        );
        logger.info("Category with id " + category.getId() + " created");
        return mapCategoryToCategoryDto(category);
    }


    public CategoryDto update(CategoryDto resource) {

        Category resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setImage(new Image(resource.getImageUrl()));

        Category category = repository.update(resourceToUpdate);
        logger.info("Category with id " + category.getId() + " updated");

        return mapCategoryToCategoryDto(category);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Category with id " + id + " deleted");
    }

    private CategoryDto mapCategoryToCategoryDto(Category category) {

        return new CategoryDto(
                category.getId(),
                category.getDateCreated(),
                category.getLastModifiedDate(),
                category.getName(),
                category.getImage().getUrl()
        );
    }
}
