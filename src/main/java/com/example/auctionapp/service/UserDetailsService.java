package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.Util.TimeUtility;
import com.example.auctionapp.dto.UserDtos.UserDetailsDto;
import com.example.auctionapp.dto.UserDtos.UserRegisterDto;
import com.example.auctionapp.enumeration.GenderEnum;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.model.Address;
import com.example.auctionapp.model.CardInformation;
import com.example.auctionapp.model.Image;
import com.example.auctionapp.model.Role;
import com.example.auctionapp.model.UserDetails;
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
public class UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
    private static final String RESOURCE_NAME = "User Details";

    private final BaseRepository<UserDetails> repository;
    private final BaseRepository<Address> addressRepository;
    private final BaseRepository<CardInformation> cardInformationRepository;

    @Autowired
    public UserDetailsService(BaseRepository<UserDetails> repository,
                              BaseRepository<Address> addressRepository,
                              BaseRepository<CardInformation> cardInformationRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.cardInformationRepository = cardInformationRepository;
    }

    public List<UserDetailsDto> getAll() {

        List<UserDetails> userDetails = repository.findAll();

        List<UserDetailsDto> userDetailsDtos = userDetails.stream().map(
                userDetail -> { return MappingUtility.mapUserDetailsToUserDetailsDto(userDetail);
                }
        ).collect(Collectors.toList());

        return  userDetailsDtos;
    }


    public UserDetailsDto getById(Long id) {

        UserDetails userDetails = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapUserDetailsToUserDetailsDto(userDetails);
    }

    public UserDetailsDto add(UserDetailsDto resource) {

        Address address =  RepositoryUtility.findIfExist(
                addressRepository,
                resource.getAddress().getId(),
                "Address"
        );

        CardInformation cardInformation =  RepositoryUtility.findIfExist(
                cardInformationRepository,
                resource.getCardInformation().getId(),
                "Card Information"
        );

        UserDetails userDetails =
                new UserDetails(
                        resource.getPhoneNumber(),
                        TimeUtility.timestampToLocalDateTime(resource.getBirthDate()),
                        GenderEnum.fromValue(resource.getGender()),
                        address,
                        cardInformation);

        logger.info("User Details with id " + userDetails.getId() + " created");

        return MappingUtility
                .mapUserDetailsToUserDetailsDto(userDetails);

    }


    public UserDetails update(UserDetailsDto resource) {

        UserDetails resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Address address =  RepositoryUtility.findIfExist(
                addressRepository,
                resource.getAddress().getId(),
                "Address"
        );

        CardInformation cardInformation =  RepositoryUtility.findIfExist(
                cardInformationRepository,
                resource.getCardInformation().getId(),
                "Card Information"
        );

        resourceToUpdate.setPhoneNumber(resource.getPhoneNumber());
        resourceToUpdate.setBirthDate(TimeUtility.timestampToLocalDateTime(resource.getBirthDate()));
        resourceToUpdate.setGender(GenderEnum.fromValue(resource.getGender()));
        resourceToUpdate.setAddress(address);
        resourceToUpdate.setCardInformation(cardInformation);

        UserDetails userDetails = repository.update(resourceToUpdate);

        logger.info("User Details with id " + userDetails.getId() + " updated");

        return userDetails;
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("User Details with id " + id + " deleted");
    }
}
