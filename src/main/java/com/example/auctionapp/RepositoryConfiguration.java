package com.example.auctionapp;

import com.example.auctionapp.model.*;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.repository.CityRepository;
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
        return new UserRepository(UserAccount.class, entityManager);
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

    @Bean
    BaseRepository<Country> countryRepository() { return new BaseRepository<>(Country.class, entityManager);}

    @Bean
    CityRepository cityRepository() { return new CityRepository(City.class, entityManager);}

    @Bean
    BaseRepository<CardInformation> cardInformationRepository() {
        return new BaseRepository<>(CardInformation.class, entityManager);
    }

    @Bean
    BaseRepository<Address> addressRepository() {
        return new BaseRepository<>(Address.class, entityManager);
    }

    @Bean
    BaseRepository<UserRegisterInformation> userRegisterInformationRepository() {
        return new BaseRepository<>(UserRegisterInformation.class, entityManager);
    }

    @Bean
    BaseRepository<UserDetails> userDetailsRepository() {
        return new BaseRepository<>(UserDetails.class, entityManager);
    }

}
