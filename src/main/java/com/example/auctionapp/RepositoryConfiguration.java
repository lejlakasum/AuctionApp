package com.example.auctionapp;

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

@Configuration
public class RepositoryConfiguration {

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

    @Bean
    BaseRepository<Rating> ratingyRepository() {
        BaseRepository<Rating> rep = new BaseRepository<Rating>();
        rep.setResourceClass(Rating.class);
        return rep;
    }

    @Bean
    CategoryRepository categoryRepository() {
        CategoryRepository rep = new CategoryRepository();
        rep.setResourceClass(Category.class);
        return rep;
    }

    @Bean
    String string() {
        String str = new String();
        return str;
    }
}
