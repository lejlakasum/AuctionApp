package com.example.auctionapp.seeder;

import com.example.auctionapp.enumeration.RoleEnum;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DatabaseSeeder {

    private BaseRepository<Role> roleRepository;

    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    public DatabaseSeeder(BaseRepository<Role> roleRepository) {
        this.roleRepository = roleRepository;
        this.roleRepository.setResourceClass(Role.class);
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
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


}
