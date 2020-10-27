package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.UserDto;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.User;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IBaseService<UserDto> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String RESOURCE_NAME = "User";
    private static final Long USER_ROLE_ID = 2L;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BaseRepository<Role> roleRepository;
    private final BaseRepository<Image> imageRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       BaseRepository<Role> roleRepository,
                       BaseRepository<Image> imageRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
    }

    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(
                user -> {return MappingUtility.mapUserToUserDto(user);
                }
        ).collect(Collectors.toList());

        return  userDtos;
    }


    public UserDto getById(Long id) {

        User user = RepositoryUtility.findIfExist(userRepository, id, RESOURCE_NAME);
        return MappingUtility.mapUserToUserDto(user);
    }


    public UserDto add(UserDto resource) {

        if(userAlreadyExist(resource.getEmail())) {
            String message = "User with email " + resource.getEmail() + " already registered";
            logger.error(message);
            throw new BadRequestException(message);
        }

        Role role = roleRepository.findById(USER_ROLE_ID);
        Image image = imageRepository.create(new Image(resource.getImageUrl()));

        User user = userRepository.create(new User(resource.getFirstName(),
                resource.getLastName(),
                resource.getEmail(),
                passwordEncoder.encode(resource.getPassword()),
                role,
                image)
        );

        logger.info("User with id " + user.getId() + " created");

        return MappingUtility.mapUserToUserDto(user);
    }


    public UserDto update(UserDto resource) {
        User resourceToUpdate = RepositoryUtility.findIfExist(userRepository, resource.getId(), RESOURCE_NAME);

        resourceToUpdate.setFirstName(resource.getFirstName());
        resourceToUpdate.setLastName(resource.getLastName());
        resourceToUpdate.setEmail(resource.getEmail());

        User user = userRepository.update(resourceToUpdate);

        logger.info("User with id " + user.getId() + " updated");

        return MappingUtility.mapUserToUserDto(user);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(userRepository, id, RESOURCE_NAME);

        userRepository.deleteById(id);

        logger.info("User with id " + id + " deleted");
    }

    private boolean userAlreadyExist(String email) {

        try {
            userRepository.findByEmail(email);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
