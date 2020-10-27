package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.SubcategoryDto;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.CategoryRepository;
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
public class SubcategoryService implements IBaseService<SubcategoryDto> {

    private static final Logger logger = LoggerFactory.getLogger(SubcategoryService.class);
    private static final String RESOURCE_NAME = "Subcategory";

    BaseRepository<Subcategory> repository;
    CategoryRepository categoryRepository;

    @Autowired
    public SubcategoryService(BaseRepository<Subcategory> repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<SubcategoryDto> getAll() {

        List<Subcategory> subcategories = repository.findAll();
        List<SubcategoryDto> subcategoryDtos = subcategories.stream().map(
                subcategory -> {return MappingUtility.mapSubcategoryToDto(subcategory);
                }
        ).collect(Collectors.toList());

        return  subcategoryDtos;
    }


    public SubcategoryDto getById(Long id) {

        Subcategory subcategory = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapSubcategoryToDto(subcategory);
    }


    public SubcategoryDto add(SubcategoryDto resource) {

        Category category = RepositoryUtility.findIfExist(categoryRepository, resource.getCategoryId(), "Category");

        Subcategory subcategory = repository.create(new Subcategory(resource.getName(), category));
        logger.info("Subcategory with id " + subcategory.getId() + " created");
        return MappingUtility.mapSubcategoryToDto(subcategory);
    }


    public SubcategoryDto update(SubcategoryDto resource) {

        Subcategory resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Category category = RepositoryUtility.findIfExist(categoryRepository, resource.getCategoryId(), "Category");

        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setCategory(category);

        Subcategory subcategory = repository.update(resourceToUpdate);
        logger.info("Subcategory with id " + subcategory.getId() + " updated");

        return MappingUtility.mapSubcategoryToDto(subcategory);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Subcategory with id " + id + " deleted");
    }
}
