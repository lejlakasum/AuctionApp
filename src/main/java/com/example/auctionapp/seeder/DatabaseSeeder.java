package com.example.auctionapp.seeder;

import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.RoleEnum;
import com.example.auctionapp.enumeration.SizeEnum;
import com.example.auctionapp.model.*;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.repository.UserRepository;
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
import java.util.List;


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
    private final BaseRepository<City> cityRepository;
    private final BaseRepository<Country> countryRepository;

    @Autowired
    public DatabaseSeeder(PasswordEncoder passwordEncoder,
                          BaseRepository<Role> roleRepository,
                          UserRepository userRepository,
                          BaseRepository<Category> categoryRepository,
                          BaseRepository<Subcategory> subcategoryRepository,
                          ProductRepository productRepository,
                          BaseRepository<Rating> ratingRepository,
                          BaseRepository<Image> imageRepository,
                          BaseRepository<City> cityRepository,
                          BaseRepository<Country> countryRepository
                          ) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.productRepository = productRepository;
        this.ratingRepository = ratingRepository;
        this.imageRepository = imageRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedUserTable();
        seedCategoryTable();
        seedSubcategoryTable();
        seedProductTable();
        seedRatingTable();
        seedCountryTable();
        seedCityTable();
    }

    private void seedCountryTable() {
        List<Country> countries = countryRepository.findAll();
        if(countries.isEmpty()) {
            countryRepository.create(new Country("Bosnia and Herzegovina"));
            countryRepository.create(new Country("Croatia"));
            countryRepository.create(new Country("Germany"));
        }
    }

    private void seedCityTable() {
        List<City> cities = cityRepository.findAll();
        if(cities.isEmpty()) {
            cityRepository.create(new City("Jajce", countryRepository.findById(1L)));
            cityRepository.create(new City("Sarajevo", countryRepository.findById(1L)));
            cityRepository.create(new City("Mostar", countryRepository.findById(1L)));

            cityRepository.create(new City("Zagreb",  countryRepository.findById(2L)));
            cityRepository.create(new City("Split", countryRepository.findById(2L)));
            cityRepository.create(new City("Varazdin", countryRepository.findById(2L)));

            cityRepository.create(new City("Berlin", countryRepository.findById(3L)));
            cityRepository.create(new City("Hamburg", countryRepository.findById(3L)));
            cityRepository.create(new City("Dresden", countryRepository.findById(3L)));
        }
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
        List<UserAccount> userAccounts = userRepository.findAll();
        if(userAccounts.isEmpty()) {
            Image image = imageRepository.create(new Image("https://cactusthemes.com/blog/wp-content/uploads/2018/01/tt_avatar_small.jpg"));
            userRepository.create(new UserAccount(
                   new UserRegisterInformation(
                           "Roger",
                           "Federer",
                           "roger@mail.com",
                           passwordEncoder.encode("password"),
                           roleRepository.findById(1L),
                           image
                   ),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                   new UserRegisterInformation( "Rafa",
                    "Nadal",
                    "rafa@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(2L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                    new UserRegisterInformation("Nole",
                    "Joker",
                    "nole@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(3L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                    new UserRegisterInformation("User",
                    "First",
                    "tester1@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(2L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                    new UserRegisterInformation("User",
                    "Second",
                    "tester2@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(2L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                    new UserRegisterInformation("Seller",
                    "First",
                    "seller1@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(3L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                    new UserRegisterInformation("Seller",
                    "Second",
                    "seller2@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(3L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                    new UserRegisterInformation("Admin",
                    "First",
                    "admin1@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(1L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            userRepository.create(new UserAccount(
                    new UserRegisterInformation("Admin",
                    "Second",
                    "admin2@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(1L),
                    image),
                    new UserDetails(new Address(), new CardInformation())
            ));

            Image image2 = imageRepository.create(new Image("https://www.shareicon.net/data/512x512/2016/07/26/802031_user_512x512.png"));
            userRepository.create(new UserAccount(
                    new UserRegisterInformation("Serena",
                    "Williams",
                    "serena@mail.com",
                    passwordEncoder.encode("password"),
                    roleRepository.findById(3L),
                    image2),
                    new UserDetails(new Address(), new CardInformation())
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
            subcategoryRepository.create(new Subcategory("Wallet", categories.get(1)));
            subcategoryRepository.create(new Subcategory("Belt", categories.get(1)));
            subcategoryRepository.create(new Subcategory("Desk", categories.get(5)));
            subcategoryRepository.create(new Subcategory("Boots", categories.get(3)));
            subcategoryRepository.create(new Subcategory("Running", categories.get(3)));
            subcategoryRepository.create(new Subcategory("Tennis", categories.get(3)));
            subcategoryRepository.create(new Subcategory("Mobile Phone", categories.get(6)));
            subcategoryRepository.create(new Subcategory("Tablet", categories.get(6)));
            subcategoryRepository.create(new Subcategory("TV", categories.get(6)));
            subcategoryRepository.create(new Subcategory("Laptop", categories.get(6)));
            subcategoryRepository.create(new Subcategory("Neckles", categories.get(2)));
            subcategoryRepository.create(new Subcategory("Bracelet", categories.get(2)));
            subcategoryRepository.create(new Subcategory("Shirt", categories.get(4)));
            subcategoryRepository.create(new Subcategory("Sports Crop", categories.get(4)));
            subcategoryRepository.create(new Subcategory("Nokia", categories.get(7)));
            subcategoryRepository.create(new Subcategory("Samsung", categories.get(7)));
            subcategoryRepository.create(new Subcategory("Dell", categories.get(8)));
            subcategoryRepository.create(new Subcategory("Hp", categories.get(8)));
            subcategoryRepository.create(new Subcategory("Acer", categories.get(8)));
            subcategoryRepository.create(new Subcategory("Desk", categories.get(9)));
            subcategoryRepository.create(new Subcategory("Bench", categories.get(9)));



            logger.info("Subcategory table seeded");
        }
    }

    private void seedProductTable() {
        List<Product> products = productRepository.findAll();
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        List<UserAccount> userAccounts = userRepository.findAll();
        UserAccount seller = userAccounts.get(2);
        UserAccount seller2 = userAccounts.get(3);
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
                                                seller,
                                                ColorEnum.BLACK,
                                                SizeEnum.MEDIUM
                                                )
            );

            images=new ArrayList<>();
            images.add(new Image("https://pngimg.com/uploads/leather_jacket/leather_jacket_PNG35.png"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F9YR98_AS05.jpg?alt=media&token=67ae4719-dfc0-40f8-8b54-d83f9ec89992"));
            productRepository.create(new Product("Brown Jacket",
                                                description,
                                                300.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(50),
                                                LocalDateTime.now(ZoneOffset.UTC).plusDays(5),
                                                images,
                                                true,
                                                seller,
                                                ColorEnum.BROWN,
                                                SizeEnum.LARGE
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
                                                seller,
                                                ColorEnum.BLUE,
                                                SizeEnum.EXTRA_LARGE
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FMens_White_Leather_Motorcycle_Jacket__45131_zoom.jpg?alt=media&token=9116965a-e04a-436b-9206-242559b88ee8"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2FMens_White_Leather_Motorcycle_Jacket__45131_zoom.jpg?alt=media&token=9116965a-e04a-436b-9206-242559b88ee8"));
            productRepository.create(new Product("White Jacket",
                                                description,
                                                250.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                false,
                                                seller,
                                                ColorEnum.WHITE,
                                                SizeEnum.SMALL
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            productRepository.create(new Product("Black Jacket",
                                                description,
                                                150.,
                                                subcategories.get(1),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                false,
                                                seller2,
                                            ColorEnum.BLACK,
                                            SizeEnum.SMALL
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
                                                seller2,
                                                ColorEnum.BLACK,
                                                SizeEnum.MEDIUM
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F1-42-9714512230_xxl.webp?alt=media&token=f772ad4c-b352-41c2-bf2c-a320c11166e5"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F1-42-9714512230_xxl.webp?alt=media&token=f772ad4c-b352-41c2-bf2c-a320c11166e5"));
            productRepository.create(new Product("Tennis Shoes",
                                                description,
                                                375.,
                                                subcategories.get(8),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                true,
                                                seller2,
                                                ColorEnum.WHITE,
                                                SizeEnum.LARGE
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            productRepository.create(new Product("Black Jacket",
                                                description,
                                                550.,
                                                subcategories.get(2),
                                                LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                                                LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                                                images,
                                                true,
                                                seller2,
                                                ColorEnum.BLACK,
                                                SizeEnum.MEDIUM
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F71320MVSdWL._SL1500_.jpg?alt=media&token=9086fc51-9fa9-4419-bfc3-c84694a7c6b6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F71320MVSdWL._SL1500_.jpg?alt=media&token=9086fc51-9fa9-4419-bfc3-c84694a7c6b6"));
            productRepository.create(new Product("Lenovo Tablet",
                            description,
                            800.,
                            subcategories.get(10),
                            LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                            LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                            images,
                            true,
                            seller2,
                            ColorEnum.BLACK,
                            SizeEnum.MEDIUM
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F1-42-9714512230_xxl.webp?alt=media&token=f772ad4c-b352-41c2-bf2c-a320c11166e5"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F1-42-9714512230_xxl.webp?alt=media&token=f772ad4c-b352-41c2-bf2c-a320c11166e5"));
            productRepository.create(new Product("Tennis Shoes",
                            description,
                            713.55,
                            subcategories.get(8),
                            LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                            LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                            images,
                            true,
                            seller2,
                            ColorEnum.WHITE,
                            SizeEnum.LARGE
                    )
            );

            images=new ArrayList<>();
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            images.add(new Image("https://firebasestorage.googleapis.com/v0/b/auction-internship-app.appspot.com/o/images%2F7324_4.jpg?alt=media&token=fd714986-4dc8-4af8-8593-071d155344c6"));
            productRepository.create(new Product("Black Jacket",
                            description,
                            1000.,
                            subcategories.get(2),
                            LocalDateTime.now(ZoneOffset.UTC).minusHours(77),
                            LocalDateTime.now(ZoneOffset.UTC).plusHours(3),
                            images,
                            true,
                            seller2,
                            ColorEnum.BLACK,
                            SizeEnum.MEDIUM
                    )
            );

            logger.info("Product table seeded");
        }
    }

    private void seedRatingTable() {
        List<Rating> ratings = ratingRepository.findAll();
        List<UserAccount> userAccounts = userRepository.findAll();
        if(ratings.isEmpty()) {

            ratingRepository.create(new Rating(5, "", userAccounts.get(2)));
            ratingRepository.create(new Rating(3, "", userAccounts.get(2)));
            ratingRepository.create(new Rating(4, "", userAccounts.get(2)));
            ratingRepository.create(new Rating(3, "", userAccounts.get(3)));
            ratingRepository.create(new Rating(3, "", userAccounts.get(3)));
            ratingRepository.create(new Rating(4, "", userAccounts.get(3)));

            logger.info("Rating table seeded");
        }
    }
}
