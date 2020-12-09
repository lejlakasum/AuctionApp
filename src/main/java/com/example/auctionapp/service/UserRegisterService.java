package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.UserDtos.UserRegisterDto;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.UserRegisterInformation;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserRegisterService{

    private static final Logger logger = LoggerFactory.getLogger(UserRegisterService.class);
    private static final String RESOURCE_NAME = "User Register Information";
    private static final Long USER_ROLE_ID = 2L;

    private final BaseRepository<UserRegisterInformation> repository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BaseRepository<Role> roleRepository;
    private final BaseRepository<Image> imageRepository;

    @Autowired
    public UserRegisterService(BaseRepository<UserRegisterInformation> repository,
                               PasswordEncoder passwordEncoder,
                               UserRepository userRepository,
                               BaseRepository<Role> roleRepository,
                               BaseRepository<Image> imageRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.imageRepository = imageRepository;
    }

    public List<UserRegisterDto> getAll() {

        List<UserRegisterInformation> userRegisterInformations = repository.findAll();

        List<UserRegisterDto> userRegisterDtos = userRegisterInformations.stream().map(
                userRegisterInfo -> { return MappingUtility.mapUserRegisterToUserRegisterDto(userRegisterInfo);
                }
        ).collect(Collectors.toList());

        return  userRegisterDtos;
    }


    public UserRegisterDto getById(Long id) {

        UserRegisterInformation userRegisterInformation = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapUserRegisterToUserRegisterDto(userRegisterInformation);
    }

    public UserRegisterInformation add(UserRegisterDto resource) {

        if(userAlreadyExist(resource.getEmail())) {
            String message = "User with email " + resource.getEmail() + " already registered";
            logger.error(message);
            throw new BadRequestException(message);
        }

        Role role = roleRepository.findById(USER_ROLE_ID);
        Image image = imageRepository.create(new Image(resource.getImageUrl()));

        UserRegisterInformation userRegisterInformation =
                new UserRegisterInformation(resource.getFirstName(),
                        resource.getLastName(),
                        resource.getEmail(),
                        passwordEncoder.encode(resource.getPassword()),
                        role,
                        image);

        logger.info("User Registration Information with id " + userRegisterInformation.getId() + " created");

        return userRegisterInformation;

    }


    public UserRegisterInformation update(UserRegisterDto resource) {

        UserRegisterInformation resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Image oldImage = imageRepository.findById(resourceToUpdate.getImage().getId());
        oldImage.setUrl(resource.getImageUrl());

        resourceToUpdate.setFirstName(resource.getFirstName());
        resourceToUpdate.setLastName(resource.getLastName());
        resourceToUpdate.setEmail(resource.getEmail());
        resourceToUpdate.setImage(oldImage);

        UserRegisterInformation userRegisterInformation = repository.update(resourceToUpdate);

        logger.info("User Registration Information with id " + userRegisterInformation.getId() + " updated");

        return userRegisterInformation;
    }

    public UserRegisterInformation updateRole(Long userRegisterId, Role role) {
        UserRegisterInformation resourceToUpdate = RepositoryUtility.findIfExist(repository, userRegisterId, RESOURCE_NAME);

        resourceToUpdate.setRole(role);

        UserRegisterInformation userRegisterInformation = repository.update(resourceToUpdate);

        logger.info("User Role with id " + userRegisterInformation.getId() + " updated");

        return userRegisterInformation;
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("User Registration Information with id " + id + " deleted");
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
