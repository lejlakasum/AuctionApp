package com.example.auctionapp.service;

import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.SubcategoryDto;
import com.example.auctionapp.model.Category;
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
public class SubcategoryService implements IBaseService<SubcategoryDto> {

    private static final String RESOURCE_NAME = "Subcategory";

    BaseRepository<Subcategory> repository;
    BaseRepository<Category> categoryRepository;

    private static Logger logger = LoggerFactory.getLogger(SubcategoryService.class);

    @Autowired
    public SubcategoryService(BaseRepository<Subcategory> repository,
                              BaseRepository<Category> categoryRepository) {
        this.repository = repository;
        this.repository.setResourceClass(Subcategory.class);
        this.categoryRepository=categoryRepository;
        this.categoryRepository.setResourceClass(Category.class);
    }


    public List<SubcategoryDto> getAll() {

        List<Subcategory> subcategories = repository.findAll();
        List<SubcategoryDto> subcategoryDtos = new ArrayList<>();

        for (Subcategory subcategory:subcategories) {
            subcategoryDtos.add(mapSubcategoryToDto(subcategory));
        }

        return  subcategoryDtos;
    }


    public SubcategoryDto getById(Long id) {

        Subcategory subcategory = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return mapSubcategoryToDto(subcategory);
    }


    public SubcategoryDto add(SubcategoryDto resource) {

        Category category = RepositoryUtility.findIfExist(categoryRepository, resource.getCategoryId(), "Category");

        Subcategory subcategory = repository.create(new Subcategory(resource.getName(), category));
        logger.info("Subcategory with id " + subcategory.getId() + " created");
        return mapSubcategoryToDto(subcategory);
    }


    public SubcategoryDto update(SubcategoryDto resource) {

        Subcategory resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Category category = RepositoryUtility.findIfExist(categoryRepository, resource.getCategoryId(), "Category");

        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setCategory(category);

        Subcategory subcategory = repository.update(resourceToUpdate);
        logger.info("Subcategory with id " + subcategory.getId() + " updated");

        return mapSubcategoryToDto(subcategory);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Subcategory with id " + id + " deleted");
    }

    private SubcategoryDto mapSubcategoryToDto(Subcategory subcategory) {
        return new SubcategoryDto(subcategory.getId(),
                subcategory.getDateCreated(),
                subcategory.getLastModifiedDate(),
                subcategory.getName(),
                subcategory.getCategory().getId()
        );
    }
}
