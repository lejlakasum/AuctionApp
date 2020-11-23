package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.UserDtos.CardInformationDto;
import com.example.auctionapp.model.CardInformation;
import com.example.auctionapp.repository.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardInformarionService implements IBaseService<CardInformationDto> {

    private static final Logger logger = LoggerFactory.getLogger(CardInformarionService.class);
    private static final String RESOURCE_NAME = "CardInformation";

    private final BaseRepository<CardInformation> repository;

    @Autowired
    public CardInformarionService(BaseRepository<CardInformation> repository) {
        this.repository = repository;
    }

    public List<CardInformationDto> getAll() {

        List<CardInformation> cardInformations = repository.findAll();

        List<CardInformationDto> cardInformationDtos = cardInformations.stream().map(
                cardInformation -> { return MappingUtility.mapCardToCardDto(cardInformation);
                }
        ).collect(Collectors.toList());

        return  cardInformationDtos;
    }


    public CardInformationDto getById(Long id) {

        CardInformation cardInformation = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapCardToCardDto(cardInformation);
    }

    public CardInformationDto add(CardInformationDto resource) {

        CardInformation cardInformation = repository.create(new CardInformation(
                resource.getNameOnCard(),
                resource.getCardNumber(),
                resource.getYearExpiration(),
                resource.getMonthExpiration(),
                resource.getCvc(),
                resource.getPaypal(),
                resource.getCreditCard()
        ));

        logger.info("Card Information with id " + cardInformation.getId() + " created");
        return MappingUtility.mapCardToCardDto(cardInformation);
    }


    public CardInformationDto update(CardInformationDto resource) {

        CardInformation resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        resourceToUpdate.setNameOnCard(resource.getNameOnCard());
        resourceToUpdate.setCardNumber(resource.getCardNumber());
        resourceToUpdate.setYearExpiration(resource.getYearExpiration());
        resourceToUpdate.setMonthExpiration(resource.getMonthExpiration());
        resourceToUpdate.setCvc(resource.getCvc());
        resourceToUpdate.setPaypal(resource.getPaypal());
        resourceToUpdate.setCreditCard(resource.getCreditCard());

        CardInformation cardInformation = repository.update(resourceToUpdate);
        logger.info("Card Information with id " + cardInformation.getId() + " updated");

        return MappingUtility.mapCardToCardDto(cardInformation);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Card Information with id " + id + " deleted");
    }
}
