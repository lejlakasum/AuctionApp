package com.example.auctionapp.seeder;

import com.example.auctionapp.enumeration.RoleEnum;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.Rating;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.ProductRepository;
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
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class DatabaseSeeder {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private final PasswordEncoder passwordEncoder;

    private final BaseRepository<Role> roleRepository;
    private final UserRepository userRepository;
    private final BaseRepository<Category> categoryRepository;
    private final BaseRepository<Subcategory> subcategoryRepository;
    private final ProductRepository productRepository;
    private final BaseRepository<Rating> ratingRepository;
    private final BaseRepository<Image> imageRepository;

    @Autowired
    public DatabaseSeeder(PasswordEncoder passwordEncoder,
                          BaseRepository<Role> roleRepository,
                          UserRepository userRepository,
                          BaseRepository<Category> categoryRepository,
                          BaseRepository<Subcategory> subcategoryRepository,
                          ProductRepository productRepository,
                          BaseRepository<Rating> ratingRepository,
                          BaseRepository<Image> imageRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.productRepository = productRepository;
        this.ratingRepository = ratingRepository;
        this.imageRepository = imageRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedUserTable();
        seedCategoryTable();
        seedSubcategoryTable();
        seedProductTable();
        seedRatingTable();
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
            Image image = imageRepository.create(new Image("https://cactusthemes.com/blog/wp-content/uploads/2018/01/tt_avatar_small.jpg"));
            userRepository.create(new User(
               "Roger",
               "Federer",
               "roger@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(1L),
                    image
            ));

            userRepository.create(new User(
                    "Rafa",
                    "Nadal",
                    "rafa@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(2L),
                    image
            ));

            userRepository.create(new User(
                    "Nole",
                    "Joker",
                    "nole@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(3L),
                    image
            ));

            Image image2 = imageRepository.create(new Image("https://www.shareicon.net/data/512x512/2016/07/26/802031_user_512x512.png"));
            userRepository.create(new User(
                    "Serena",
                    "Williams",
                    "serena@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(3L),
                    image2
            ));

            logger.info("User table seeded");
        }
    }

    private void seedCategoryTable() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            categoryRepository.create(new Category("Fashion", new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6")));
            categoryRepository.create(new Category("Accessories", new Image("#")));
            categoryRepository.create(new Category("Jewelry", new Image("#")));
            categoryRepository.create(new Category("Shoes", new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F1-42-9714512230_xxl.webp?alt=media&token=f772ad4c-b352-41c2-bf2c-a320c11166e5")));
            categoryRepository.create(new Category("Sportswear", new Image("#")));
            categoryRepository.create(new Category("Home", new Image("#")));
            categoryRepository.create(new Category("Electronics", new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F71320MVSdWL._SL1500_.jpg?alt=media&token=9086fc51-9fa9-4419-bfc3-c84694a7c6b6")));
            categoryRepository.create(new Category("Mobile", new Image("#")));
            categoryRepository.create(new Category("Computer", new Image("#")));
            categoryRepository.create(new Category("Garden", new Image("#")));

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
            subcategoryRepository.create(new Subcategory("Boots", categories.get(3)));
            subcategoryRepository.create(new Subcategory("Running", categories.get(3)));
            subcategoryRepository.create(new Subcategory("Tennis", categories.get(3)));
            subcategoryRepository.create(new Subcategory("Mobile Phone", categories.get(6)));
            subcategoryRepository.create(new Subcategory("Tablet", categories.get(6)));
            subcategoryRepository.create(new Subcategory("TV", categories.get(6)));
            subcategoryRepository.create(new Subcategory("Laptop", categories.get(6)));


            logger.info("Subcategory table seeded");
        }
    }

    private void seedProductTable() {
        List<Product> products = productRepository.findAll();
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        List<User> users = userRepository.findAll();
        User seller = users.get(2);
        User seller2 = users.get(3);
        List<Image> images = new ArrayList<>();
        if(products.isEmpty()) {
            final String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";


            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_1.jpg?alt=media&token=8f659c7a-c046-416c-b7e5-25e91204eb9e"));
            productRepository.create(new Product("Black Jacket",
                                                description,
                                                100.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(35),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(22),
                                                images,
                                                false,
                                                seller
                                                )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F9YR98_AS05.jpg?alt=media&token=67ae4719-dfc0-40f8-8b54-d83f9ec89992"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F9YR98_AS05.jpg?alt=media&token=67ae4719-dfc0-40f8-8b54-d83f9ec89992"));
            productRepository.create(new Product("Brown Jacket",
                                                description,
                                                300.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(50),
                                                LocalDateTime.now(ZoneOffset.UTC).plusDays(5),
                                                images,
                                                true,
                                                seller
                                                )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FCMW772_UR5_01_bynder_defined_type_product_01.jpg?alt=media&token=3fcce00d-42d4-47bd-931f-57b64dd9aafa"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FCMW772_UR5_01_bynder_defined_type_product_01.jpg?alt=media&token=3fcce00d-42d4-47bd-931f-57b64dd9aafa"));
            productRepository.create(new Product("Blue Jacket",
                                                description,
                                                500.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(3),
                                                LocalDateTime.now(ZoneOffset.UTC).plusDays(3),
                                                images,
                                                true,
                                                seller
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FMens_White_Leather_Motorcycle_Jacket__45131_zoom.jpg?alt=media&token=9116965a-e04a-436b-9206-242559b88ee8"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FMens_White_Leather_Motorcycle_Jacket__45131_zoom.jpg?alt=media&token=9116965a-e04a-436b-9206-242559b88ee8"));
            productRepository.create(new Product("White Jacket",
                                                description,
                                                1000.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                false,
                                                seller
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            productRepository.create(new Product("White Jacket",
                                                description,
                                                1000.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                false,
                                                seller2
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F71320MVSdWL._SL1500_.jpg?alt=media&token=9086fc51-9fa9-4419-bfc3-c84694a7c6b6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F71320MVSdWL._SL1500_.jpg?alt=media&token=9086fc51-9fa9-4419-bfc3-c84694a7c6b6"));
            productRepository.create(new Product("Lenovo Tablet",
                                                description,
                                                1000.,
                                                subcategories.get(10),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                true,
                                                seller2
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F1-42-9714512230_xxl.webp?alt=media&token=f772ad4c-b352-41c2-bf2c-a320c11166e5"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F1-42-9714512230_xxl.webp?alt=media&token=f772ad4c-b352-41c2-bf2c-a320c11166e5"));
            productRepository.create(new Product("Tennis shoes",
                                                description,
                                                1000.,
                                                subcategories.get(8),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                true,
                                                seller2
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            productRepository.create(new Product("White Jacket",
                                                description,
                                                1000.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                true,
                                                seller2
                    )
            );

            logger.info("Product table seeded");
        }
    }

    private void seedRatingTable() {
        List<Rating> ratings = ratingRepository.findAll();
        List<User> users = userRepository.findAll();
        if(ratings.isEmpty()) {

            ratingRepository.create(new Rating(5, "", users.get(2)));
            ratingRepository.create(new Rating(3, "", users.get(2)));
            ratingRepository.create(new Rating(4, "", users.get(2)));
            ratingRepository.create(new Rating(3, "", users.get(3)));
            ratingRepository.create(new Rating(3, "", users.get(3)));
            ratingRepository.create(new Rating(4, "", users.get(3)));

            logger.info("Rating table seeded");
        }
    }
}
