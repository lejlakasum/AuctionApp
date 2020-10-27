package com.example.auctionapp;

import com.example.auctionapp.model.Bid;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Rating;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class RepositoryConfiguration {

    @PersistenceContext
    EntityManager entityManager;

    @Bean
    BaseRepository<Image> imageRepository() {
        return new BaseRepository<>(Image.class, entityManager);
    }

    @Bean
    BaseRepository<Role> roleRepository() {
        return new BaseRepository<>(Role.class, entityManager);
    }

    @Bean
    ProductRepository productRepository() {
        return new ProductRepository(Product.class, entityManager);
    }

    @Bean
    BaseRepository<Subcategory> subcategoryRepository() {
        return new BaseRepository<>(Subcategory.class, entityManager);
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepository(User.class, entityManager);
    }

    @Bean
    BaseRepository<Rating> ratingRepository() {
        return new BaseRepository<>(Rating.class, entityManager);
    }

    @Bean
    CategoryRepository categoryRepository() {
        return new CategoryRepository(Category.class, entityManager);
    }

    @Bean
    BaseRepository<Bid> bidRepository() {
        return new BaseRepository<>(Bid.class, entityManager);
    }

}
