package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.Util.TimeUtility;
import com.example.auctionapp.dto.UserBidDto;
import com.example.auctionapp.dto.UserDtos.UserAccountDto;
import com.example.auctionapp.enumeration.GenderEnum;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.model.*;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IBaseService<UserAccountDto> {

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

    public List<UserAccountDto> getAll() {

        List<UserAccount> userAccounts = userRepository.findAll();
        List<UserAccountDto> userAccountDtos = userAccounts.stream().map(
                user -> {return MappingUtility.mapUserToUserDto(user);
                }
        ).collect(Collectors.toList());

        return userAccountDtos;
    }


    public UserAccountDto getById(Long id) {

        UserAccount userAccount = RepositoryUtility.findIfExist(userRepository, id, RESOURCE_NAME);
        return MappingUtility.mapUserToUserDto(userAccount);
    }

    public List<UserBidDto> getBidsByUser(Long userId) {

        List<Bid> bids = userRepository.getBidsByUser(userId);

        List<UserBidDto> userBids = bids.stream().map(bid -> {
            return MappingUtility.mapBidToUserBidDto(bid);
        }).collect(Collectors.toList());

        return userBids;
    }

    public UserAccountDto add(UserAccountDto resource) {

        if(userAlreadyExist(resource.getUserRegister().getEmail())) {
            String message = "User with email " + resource.getUserRegister().getEmail() + " already registered";
            logger.error(message);
            throw new BadRequestException(message);
        }

        Role role = roleRepository.findById(USER_ROLE_ID);
        Image image = imageRepository.create(new Image(resource.getUserRegister().getImageUrl()));

        UserAccount userAccount = userRepository.create(
                new UserAccount(
                        new UserRegisterInformation(resource.getUserRegister().getFirstName(),
                                resource.getUserRegister().getLastName(),
                                resource.getUserRegister().getEmail(),
                                passwordEncoder.encode(resource.getUserRegister().getPassword()),
                                role,
                                image),
                        new UserDetails(new Address(new City(new Country())), new CardInformation())
                )
        );

        logger.info("User with id " + userAccount.getId() + " created");

        return MappingUtility.mapUserToUserDto(userAccount);
    }


    public UserAccountDto update(UserAccountDto resource) {
        UserAccount resourceToUpdate = RepositoryUtility.findIfExist(userRepository, resource.getId(), RESOURCE_NAME);

        //TODO updating
        return MappingUtility.mapUserToUserDto(resourceToUpdate);
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
