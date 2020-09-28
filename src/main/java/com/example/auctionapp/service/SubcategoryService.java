package com.example.auctionapp.service;

import com.example.auctionapp.dto.SubcategoryDto;
import com.example.auctionapp.exception.NotFoundException;
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

    BaseRepository<Subcategory> repository;
    BaseRepository<Category> categoryRepository;

    private Logger logger = LoggerFactory.getLogger(SubcategoryService.class);

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
            subcategoryDtos.add(new SubcategoryDto(subcategory.getId(), subcategory.getDateCreated(), subcategory.getLastModifiedDate(), subcategory.getName(), subcategory.getCategory().getId()));
        }

        return  subcategoryDtos;
    }


    public SubcategoryDto getById(Long id) {

        Subcategory subcategory = repository.findById(id);
        if(subcategory == null) {
            String message = "Subcategory with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }
        return new SubcategoryDto(subcategory.getId(), subcategory.getDateCreated(), subcategory.getLastModifiedDate(),
                subcategory.getName(), subcategory.getCategory().getId());
    }


    public SubcategoryDto add(SubcategoryDto resource) {

        Category category = categoryRepository.findById(resource.getCategoryId());
        if(category==null) {
            String message = "Category with id " + resource.getId() + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        Subcategory subcategory = repository.create(new Subcategory(resource.getName(), category));
        logger.info("Subcategory with id " + subcategory.getId() + " created");
        return new SubcategoryDto(subcategory.getId(), subcategory.getDateCreated(), subcategory.getLastModifiedDate(),
                subcategory.getName(), subcategory.getCategory().getId());
    }


    public SubcategoryDto update(SubcategoryDto resource) {
        Subcategory resourceToUpdate = repository.findById(resource.getId());
        if(resourceToUpdate == null) {
            String message = "Subcategory with id " + resource.getId() + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        Category category = categoryRepository.findById(resource.getCategoryId());
        if(category==null) {
            String message = "Category with id " + resource.getId() + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setCategory(category);

        Subcategory subcategory = repository.update(resourceToUpdate);
        logger.info("Subcategory with id " + subcategory.getId() + " updated");

        return new SubcategoryDto(subcategory.getId(), subcategory.getDateCreated(), subcategory.getLastModifiedDate(), subcategory.getName(), subcategory.getCategory().getId());
    }


    public void deleteById(Long id) {

        if(repository.findById(id) == null) {
            String message = "Subcategory with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        repository.deleteById(id);
        logger.info("Subcategory with id " + id + " deleted");
    }
}
