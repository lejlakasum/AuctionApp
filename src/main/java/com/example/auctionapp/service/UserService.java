package com.example.auctionapp.service;

import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.UserDto;
import com.example.auctionapp.exception.BadRequestException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IBaseService<UserDto> {

    private static final String RESOURCE_NAME = "User";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaseRepository<Role> roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Long USER_ROLE_ID = 2L;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAll() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(
                user -> {return mapUserToUserDto(user);
                }
        ).collect(Collectors.toList());

        return  userDtos;
    }


    public UserDto getById(Long id) {

        User user = RepositoryUtility.findIfExist(userRepository, id, RESOURCE_NAME);
        return mapUserToUserDto(user);
    }


    public UserDto add(UserDto resource) {

        if(userAlreadyExist(resource.getEmail())) {
            String message = "User with email " + resource.getEmail() + " already registered";
            logger.error(message);
            throw new BadRequestException(message);
        }

        Role role = roleRepository.findById(USER_ROLE_ID);

        User user = userRepository.create(new User(resource.getFirstName(),
                resource.getLastName(),
                resource.getEmail(),
                passwordEncoder.encode(resource.getPassword()),
                role)
        );

        logger.info("User with id " + user.getId() + " created");

        return mapUserToUserDto(user);
    }


    public UserDto update(UserDto resource) {
        User resourceToUpdate = RepositoryUtility.findIfExist(userRepository, resource.getId(), RESOURCE_NAME);

        resourceToUpdate.setFirstName(resource.getFirstName());
        resourceToUpdate.setLastName(resource.getLastName());
        resourceToUpdate.setEmail(resource.getEmail());

        User user = userRepository.update(resourceToUpdate);

        logger.info("User with id " + user.getId() + " updated");

        return mapUserToUserDto(user);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(userRepository, id, RESOURCE_NAME);

        userRepository.deleteById(id);

        logger.info("User with id " + id + " deleted");
    }

    private UserDto mapUserToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getDateCreated(),
                user.getLastModifiedDate(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getId()
        );
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
