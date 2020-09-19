package com.example.auctionapp.service;

import com.example.auctionapp.dto.UserDto;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.exception.NotFoundException;
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

@Service
@Transactional
public class UserService implements IBaseService<UserDto> {

    private BaseRepository<User> baseUserRepository;
    private UserRepository userRepository;
    private BaseRepository<Role> roleRepository;
    private final PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public UserService(PasswordEncoder passwordEncoder,
                       BaseRepository<User> baseUserRepository,
                       UserRepository userRepository,
                       BaseRepository<Role> roleRepository) {
        this.baseUserRepository = baseUserRepository;
        this.baseUserRepository.setResourceClass(User.class);
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.roleRepository.setResourceClass(Role.class);
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> getAll() {

        List<User> users = baseUserRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user:users) {
            userDtos.add(mapUserToUserDto(user));
        }

        return  userDtos;
    }


    public UserDto getById(Long id) {

        User user = baseUserRepository.findById(id);
        if(user == null) {
            String message = "User with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }
        return mapUserToUserDto(user);
    }


    public UserDto add(UserDto resource) {

        if(userAlreadyExist(resource.getEmail())) {
            String message = "User with email " + resource.getEmail() + " already registered";
            logger.error(message);
            throw new BadRequestException(message);
        }

        Role role = roleRepository.findById(resource.getRoleId());
        if(role==null) {
            String message = "Role with id " + resource.getRoleId() + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        User user = baseUserRepository.create(new User(resource.getFirstName(),
                resource.getLastName(),
                resource.getEmail(),
                passwordEncoder.encode(resource.getPassword()),
                role));

        logger.info("User with id " + user.getId() + " created");

        return mapUserToUserDto(user);
    }


    public UserDto update(UserDto resource) {
        User resourceToUpdate = baseUserRepository.findById(resource.getId());
        if(resourceToUpdate == null) {
            String message = "User with id " + resource.getId() + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        resourceToUpdate.setFirstName(resource.getFirstName());
        resourceToUpdate.setLastName(resource.getLastName());
        resourceToUpdate.setEmail(resource.getEmail());

        User user = baseUserRepository.update(resourceToUpdate);

        logger.info("User with id " + user.getId() + " updated");

        return mapUserToUserDto(user);
    }


    public void deleteById(Long id) {

        if(baseUserRepository.findById(id) == null) {
            String message = "User with id " + id + " does not exist";
            logger.error(message);
            throw new NotFoundException(message);
        }

        baseUserRepository.deleteById(id);

        logger.info("User with id " + id + " deleted");
    }

    private UserDto mapUserToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getDateCreated(),
                user.getLastModifiedDate(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getId());
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
