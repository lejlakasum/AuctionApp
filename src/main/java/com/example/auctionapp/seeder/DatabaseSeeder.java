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
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class DatabaseSeeder {

    private BaseRepository<Role> roleRepository;
    private UserRepository userRepository;
    private BaseRepository<Category> categoryRepository;
    private BaseRepository<Subcategory> subcategoryRepository;
    private BaseRepository<Product> productRepository;
    private BaseRepository<Image> imageRepository;

    private static Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(BaseRepository<Role> roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          BaseRepository<Category> categoryRepository,
                          BaseRepository<Subcategory> subcategoryRepository,
                          BaseRepository<Product> productRepository,
                          BaseRepository<Image> imageRepository) {
        this.roleRepository = roleRepository;
        this.roleRepository.setResourceClass(Role.class);
        this.userRepository = userRepository;
        this.userRepository.setResourceClass(User.class);
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.categoryRepository.setResourceClass(Category.class);
        this.subcategoryRepository=subcategoryRepository;
        this.subcategoryRepository.setResourceClass(Subcategory.class);
        this.productRepository = productRepository;
        this.productRepository.setResourceClass(Product.class);
        this.imageRepository=imageRepository;
        this.imageRepository.setResourceClass(Image.class);
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedUserTable();
        seedCategoryTable();
        seedSubcategoryTable();
        seedProductTable();
        seedImageTable();
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
        if(products.isEmpty()) {
            productRepository.create(new Product("Black Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 100., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now(), null));
            productRepository.create(new Product("Brown Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 300., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now(), null));
            productRepository.create(new Product("Blue Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 500., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now(), null));
            productRepository.create(new Product("White Jacket", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", 1000., subcategories.get(2), LocalDateTime.now(), LocalDateTime.now(), null));
        }
    }

    private void seedImageTable() {
        List<Product> products = productRepository.findAll();
        List<Image> images = imageRepository.findAll();

        if(images.isEmpty()) {
            imageRepository.create(new Image("https://www.harrysarmysurplus.net/assets/images/rothco/7324_4.jpg", products.get(0)));
            imageRepository.create(new Image("https://www.harrysarmysurplus.net/assets/images/rothco/7324_1.jpg", products.get(0)));
            imageRepository.create(new Image("https://static.grainger.com/rp/s/is/image/Grainger/9YR98_AS05?$zmmain$", products.get(1)));
            imageRepository.create(new Image("https://content.regatta.com/CMW772_UR5/350/CMW772_UR5_01_bynder_defined_type_product_01.jpg", products.get(2)));
            imageRepository.create(new Image("https://www.fjackets.com/product_images/i/088/Mens_White_Leather_Motorcycle_Jacket__45131_zoom.jpg", products.get(3)));
        }
    }

}
