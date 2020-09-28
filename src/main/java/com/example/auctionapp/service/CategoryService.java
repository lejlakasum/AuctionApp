package com.example.auctionapp.service;

import com.example.auctionapp.dto.CategoryDto;
import com.example.auctionapp.exception.NotFoundException;
import com.example.auctionapp.model.Category;
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
public class CategoryService implements IBaseService<CategoryDto> {

    BaseRepository<Category> repository;

    private Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    public CategoryService(BaseRepository<Category> repository) {
        this.repository = repository;
        this.repository.setResourceClass(Category.class);
    }

    public List<CategoryDto> getAll() {

        List<Category> categories = repository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category:categories) {
            categoryDtos.add(new CategoryDto(category.getId(), category.getDateCreated(), category.getLastModifiedDate(), category.getName()));
        }

        return  categoryDtos;
    }


    public CategoryDto getById(Long id) {

        Category category = repository.findById(id);
        if(category == null) {
            String message = "Category with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }
        return new CategoryDto(category.getId(), category.getDateCreated(), category.getLastModifiedDate(), category.getName());
    }


    public CategoryDto add(CategoryDto resource) {
        Category category = repository.create(new Category(resource.getName()));
        logger.info("Category with id " + category.getId() + " created");
        return new CategoryDto(category.getId(), category.getDateCreated(), category.getLastModifiedDate(), category.getName());
    }


    public CategoryDto update(CategoryDto resource) {
        Category resourceToUpdate = repository.findById(resource.getId());
        if(resourceToUpdate == null) {
            String message = "Category with id " + resource.getId() + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }
        resourceToUpdate.setName(resource.getName());

        Category category = repository.update(resourceToUpdate);
        logger.info("Category with id " + category.getId() + " updated");

        return new CategoryDto(category.getId(), category.getDateCreated(), category.getLastModifiedDate(), category.getName());
    }


    public void deleteById(Long id) {

        if(repository.findById(id) == null) {
            String message = "Category with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        repository.deleteById(id);
        logger.info("Category with id " + id + " deleted");
    }
}
