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

    private final UserRepository userRepository;

    private final UserRegisterService userRegisterService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserRegisterService userRegisterService,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userRegisterService = userRegisterService;
        this.userDetailsService = userDetailsService;
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


        UserRegisterInformation userRegisterInformation = userRegisterService.add(resource.getUserRegister());


        UserAccount userAccount = userRepository.create(
                new UserAccount(
                        userRegisterInformation,
                        new UserDetails(new Address(), new CardInformation())
                )
        );

        logger.info("User with id " + userAccount.getId() + " created");

        return MappingUtility.mapUserToUserDto(userAccount);
    }


    public UserAccountDto update(UserAccountDto resource) {
        UserAccount resourceToUpdate = RepositoryUtility.findIfExist(userRepository, resource.getId(), RESOURCE_NAME);

        UserRegisterInformation userRegisterInformation = userRegisterService.update(resource.getUserRegister());
        UserDetails userDetails = userDetailsService.update(resource.getUserDetails());

        resourceToUpdate.setUserLoginInformation(userRegisterInformation);
        resourceToUpdate.setUserDetails(userDetails);

        UserAccount userAccount = userRepository.update(resourceToUpdate);
        logger.info("User with id " + resource.getId() + " updated");

        return MappingUtility.mapUserToUserDto(userAccount);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(userRepository, id, RESOURCE_NAME);

        userRepository.deleteById(id);

        logger.info("User with id " + id + " deleted");
    }


}
