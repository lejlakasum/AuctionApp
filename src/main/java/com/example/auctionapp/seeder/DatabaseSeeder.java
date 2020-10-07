package com.example.auctionapp.seeder;

import com.example.auctionapp.enumeration.RoleEnum;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.UserRepository;
import com.example.auctionapp.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class DatabaseSeeder {

    @Autowired
    private BaseRepository<Role> roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaseRepository<Category> categoryRepository;
    @Autowired
    private BaseRepository<Subcategory> subcategoryRepository;
    @Autowired
    private BaseRepository<Product> productRepository;

    private static Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedUserTable();
        seedCategoryTable();
        seedSubcategoryTable();
        seedProductTable();
    }

    private void seedRoleTable() {
        List<Role> roles = roleRepository.findAll();
        if(roles.isEmpty()) {
            roleRepository.create(new Role(RoleEnum.ADMIN.name()));
            roleRepository.create(new Role(RoleEnum.USER.name()));
            roleRepository.create(new Role(RoleEnum.SELLER.name()));

            logger.info("Role table seeded");
        }
    }

    private void seedUserTable() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            userRepository.create(new User(
               "Roger",
               "Federer",
               "roger@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(1L)
            ));

            userRepository.create(new User(
                    "Rafa",
                    "Nadal",
                    "rafa@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(2L)
            ));

            logger.info("User table seeded");
        }
    }

    private void seedCategoryTable() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            categoryRepository.create(new Category("Fashion"));
            categoryRepository.create(new Category("Accessories"));
            categoryRepository.create(new Category("Jewelry"));
            categoryRepository.create(new Category("Shoes"));
            categoryRepository.create(new Category("Sportswear"));
            categoryRepository.create(new Category("Home"));
            categoryRepository.create(new Category("Electronics"));
            categoryRepository.create(new Category("Mobile"));
            categoryRepository.create(new Category("Computer"));
            categoryRepository.create(new Category("Garden"));

            logger.info("Category table seeded");
        }
    }

    private void seedSubcategoryTable() {
        List<Category> categories = categoryRepository.findAll();
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        if(subcategories.isEmpty()) {

            subcategoryRepository.create(new Subcategory("Dress", categories.get(0)));
            subcategoryRepository.create(new Subcategory("Shirt", categories.get(0)));
            subcategoryRepository.create(new Subcategory("Jacket", categories.get(0)));
            subcategoryRepository.create(new Subcategory("Waller", categories.get(1)));
            subcategoryRepository.create(new Subcategory("Belt", categories.get(1)));
            subcategoryRepository.create(new Subcategory("Desk", categories.get(5)));

            logger.info("Subcategory table seeded");
        }
    }

    private void seedProductTable() {
        List<Product> products = productRepository.findAll();
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        List<Image> images = new ArrayList<>();

        if(products.isEmpty()) {
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_1.jpg?alt=media&token=8f659c7a-c046-416c-b7e5-25e91204eb9e"));
            productRepository.create(new Product("Black Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 100., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now().plusDays(2), images, false));
            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F9YR98_AS05.jpg?alt=media&token=67ae4719-dfc0-40f8-8b54-d83f9ec89992"));
            productRepository.create(new Product("Brown Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 300., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now().plusDays(5), images, true));
            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FCMW772_UR5_01_bynder_defined_type_product_01.jpg?alt=media&token=3fcce00d-42d4-47bd-931f-57b64dd9aafa"));
            productRepository.create(new Product("Blue Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 500., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now().plusDays(3), images, true));
            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FMens_White_Leather_Motorcycle_Jacket__45131_zoom.jpg?alt=media&token=9116965a-e04a-436b-9206-242559b88ee8"));
            productRepository.create(new Product("White Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 1000., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now().plusDays(4), images, false));

            logger.info("Product table seeded");
        }
    }
}
