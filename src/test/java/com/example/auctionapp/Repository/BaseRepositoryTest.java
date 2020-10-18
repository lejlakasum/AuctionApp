package com.example.auctionapp.Repository;

import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.repository.BaseRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class BaseRepositoryTest {

    private BaseRepository<Category> categoryRepository;

    @Mock
    private EntityManager entityManager;

    @BeforeEach
    public void init() {

        MockitoAnnotations.initMocks(this);
        categoryRepository = new BaseRepository<>(Category.class, entityManager);
    }


    @Test
    public void findCategoryTest() {

        Category category = new Category("categoryName", new Image("#"));

        Mockito.when(entityManager.find(Category.class, 1L))
                .thenReturn(category);

        Category serviceCategory = categoryRepository.findById(1L);
        assertEquals(category.getName(), serviceCategory.getName());
    }

    @Test
    public void createCategoryTest() {

        Category category = new Category("categoryName", new Image("#"));

        Mockito.doNothing().when(entityManager).persist(category);

        assertEquals(category.getName(), categoryRepository.create(category).getName());
    }
}
