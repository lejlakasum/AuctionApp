package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.UserDtos.CityDto;
import com.example.auctionapp.model.City;
import com.example.auctionapp.model.Country;
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
public class CityService implements IBaseService<CityDto> {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);
    private static final String RESOURCE_NAME = "City";

    CityRepository repository;
    BaseRepository<Country> countryRepository;

    @Autowired
    public CityService(CityRepository repository, BaseRepository<Country> countryRepository) {
        this.repository = repository;
        this.countryRepository = countryRepository;
    }

    public List<CityDto> getAll() {

        List<City> cities = repository.findAll();
        List<CityDto> cityDtos = cities.stream().map(
                city -> {return MappingUtility.mapCityToCityDto(city);
                }
        ).collect(Collectors.toList());

        return  cityDtos;
    }


    public CityDto getById(Long id) {

        City city = RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        return MappingUtility.mapCityToCityDto(city);
    }


    public CityDto add(CityDto resource) {

        Country country = RepositoryUtility.findIfExist(countryRepository, resource.getCountryId(), "Country");

        City city = repository.create(new City(resource.getName(), country));
        logger.info("City with id " + city.getId() + " created");
        return MappingUtility.mapCityToCityDto(city);
    }


    public CityDto update(CityDto resource) {

        City resourceToUpdate = RepositoryUtility.findIfExist(repository, resource.getId(), RESOURCE_NAME);

        Country country = RepositoryUtility.findIfExist(countryRepository, resource.getCountryId(), "Country");

        resourceToUpdate.setName(resource.getName());
        resourceToUpdate.setCountry(country);

        City city = repository.update(resourceToUpdate);
        logger.info("City with id " + city.getId() + " updated");

        return MappingUtility.mapCityToCityDto(city);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(repository, id, RESOURCE_NAME);

        repository.deleteById(id);
        logger.info("City with id " + id + " deleted");
    }
}
