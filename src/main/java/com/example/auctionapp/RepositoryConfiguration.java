package com.example.auctionapp;

import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    BaseRepository<Category> categoryRepository() {
        BaseRepository<Category> rep = new BaseRepository<Category>();
        rep.setResourceClass(Category.class);
        return rep;
    }

    @Bean
    BaseRepository<Image> imageRepository() {
        BaseRepository<Image> rep = new BaseRepository<Image>();
        rep.setResourceClass(Image.class);
        return rep;
    }

    @Bean
    BaseRepository<Role> roleRepository() {
        BaseRepository<Role> rep = new BaseRepository<Role>();
        rep.setResourceClass(Role.class);
        return rep;
    }

    @Bean
    ProductRepository productRepository() {
        ProductRepository rep = new ProductRepository();
        rep.setResourceClass(Product.class);
        return rep;
    }

    @Bean
    BaseRepository<Subcategory> subcategoryRepository() {
        BaseRepository<Subcategory> rep = new BaseRepository<Subcategory>();
        rep.setResourceClass(Subcategory.class);
        return rep;
    }

    @Bean
    UserRepository userRepository() {
        UserRepository rep = new UserRepository();
        rep.setResourceClass(User.class);
        return rep;
    }
}
