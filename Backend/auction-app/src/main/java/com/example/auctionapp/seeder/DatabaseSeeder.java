package com.example.auctionapp.seeder;

import com.example.auctionapp.enumeration.RoleEnum;
import com.example.auctionapp.model.Role;
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
import java.util.List;

@Component
@Transactional
public class DatabaseSeeder {

    private BaseRepository<Role> roleRepository;
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(RoleService.class);
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(BaseRepository<Role> roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.roleRepository.setResourceClass(Role.class);
        this.userRepository = userRepository;
        this.userRepository.setResourceClass(User.class);
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedUserTable();
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


}
