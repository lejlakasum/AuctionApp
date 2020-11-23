package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.UserDtos.AddressDto;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.model.Address;
import com.example.auctionapp.model.City;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressService implements IBaseService<AddressDto> {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
    private static final String RESOURCE_NAME = "Address";

    BaseRepository<Address> repository;
    CityRepository cityRepository;

    @Autowired
    public AddressService(BaseRepository<Address> repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    public List<AddressDto> getAll() {

        List<Address> addresses = repository.findAll();
        List<AddressDto> addressDtos = addresses.stream().map(
                address -> {return MappingUtility.mapAddressToAddressDto(address);
                }
        ).collect(Collectors.toList());

        return  addressDtos;
    }


    public AddressDto getById(Long id) {

        Address address = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapAddressToAddressDto(address);
    }


    public AddressDto add(AddressDto resource) {

        City city = RepositoryUtility.findIfExist(cityRepository, resource.getCity().getId(), "City");

        Address address = repository.create(new Address(resource.getStreet(), resource.getState(), resource.getZipCode(), city));

        logger.info("Address with id " + address.getId() + " created");
        return MappingUtility.mapAddressToAddressDto(address);
    }


    public AddressDto update(AddressDto resource) {

        Address resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        City city = cityRepository.findByName(resource.getCity().getName());
        if(city == null) {
            throw new BadRequestException("Invalid city name");
        }

        resourceToUpdate.setStreet(resource.getStreet());
        resourceToUpdate.setState(resource.getState());
        resourceToUpdate.setZipCode(resource.getZipCode());
        resourceToUpdate.setCity(city);

        Address address = repository.update(resourceToUpdate);
        logger.info("Address with id " + address.getId() + " updated");

        return MappingUtility.mapAddressToAddressDto(address);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("Address with id " + id + " deleted");
    }
}
