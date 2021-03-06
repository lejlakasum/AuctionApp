package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.Util.TimeUtility;
import com.example.auctionapp.dto.BidDto;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.model.Bid;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.model.UserAccount;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.ProductRepository;
import com.example.auctionapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BidService implements IBaseService<BidDto> {

    private static final Logger logger = LoggerFactory.getLogger(BidService.class);
    private static final String RESOURCE_NAME = "Bid";

    private final BaseRepository<Bid> bidRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public BidService(BaseRepository<Bid> bidRepository,
                      ProductRepository productRepository,
                      UserRepository userRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<BidDto> getAll() {

        List<Bid> bids = bidRepository.findAll();

        List<BidDto> bidDtos = bids.stream().map(
                category -> { return MappingUtility.mapBidToBidDto(category);
                }
        ).collect(Collectors.toList());

        return  bidDtos;
    }

    public BidDto getById(Long id) {

        Bid bid = RepositoryUtility.findIfExist(bidRepository, id, RESOURCE_NAME);

        return MappingUtility.mapBidToBidDto(bid);
    }

    public BidDto add(BidDto resource) {

        if(!validateBidDto(resource)) {
            throw new BadRequestException("Bid is not valid");
        }

        Product product = RepositoryUtility.findIfExist(productRepository, resource.getProductId(), "Product");
        UserAccount userAccount = RepositoryUtility.findIfExist(userRepository, resource.getUserId(), "User");
        Bid bid = bidRepository.create(new Bid(
                userAccount,
                product,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(resource.getBidTime()), ZoneOffset.UTC),
                resource.getBidAmount()));
        logger.info("Bid with id " + bid.getId() + " created");

        return MappingUtility.mapBidToBidDto(bid);
    }

    public BidDto update(BidDto resource) {

        if(!validateBidDto(resource)) {
            throw new BadRequestException("Bid is not valid");
        }

        Bid resourceToUpdate = RepositoryUtility.findIfExist(bidRepository, resource.getId(), RESOURCE_NAME);

        resourceToUpdate.setBidTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(resource.getBidTime()), ZoneOffset.UTC));
        resourceToUpdate.setBidAmount(resource.getBidAmount());

        Bid bid = bidRepository.update(resourceToUpdate);
        logger.info("Bid with id " + bid.getId() + " updated");

        return MappingUtility.mapBidToBidDto(bid);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(bidRepository, id, RESOURCE_NAME);

        bidRepository.deleteById(id);
        logger.info("Bid with id " + id + " deleted");
    }

    private boolean validateBidDto(BidDto bidDto) {

        Product product = productRepository.findById(bidDto.getProductId());
        Double highestBid = product.getBids().stream().mapToDouble(bid -> bid.getBidAmount()).max().orElse(0);

        if (bidDto.getBidAmount() <= highestBid) {
            return false;
        }

        Long auctionStart = TimeUtility.LocalDateTimeToTimestamp(product.getAuctionStartDate());
        Long auctionEnd = TimeUtility.LocalDateTimeToTimestamp(product.getAuctionEndDate());

        if(bidDto.getBidTime() <= auctionStart || bidDto.getBidTime() >= auctionEnd) {
            return false;
        }

        return true;
    }

}
