package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.Util.RepositoryUtility;
import com.example.auctionapp.dto.UserDtos.CountryDto;
import com.example.auctionapp.model.Country;
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
public class CountryService implements IBaseService<CountryDto> {

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);
    private static final String RESOURCE_NAME = "Country";

    private final BaseRepository<Country> countryRepository;

    @Autowired
    public CountryService(BaseRepository<Country> countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryDto> getAll() {

        List<Country> countries = countryRepository.findAll();

        List<CountryDto> countryDto = countries.stream().map(
                country -> { return MappingUtility.mapCountryToCountryDto(country);
                }
        ).collect(Collectors.toList());

        return countryDto;
    }

    public CountryDto getById(Long id) {

        Country country = RepositoryUtility.findIfExist(countryRepository, id, RESOURCE_NAME);

        return MappingUtility.mapCountryToCountryDto(country);
    }

    public CountryDto add(CountryDto resource) {
        Country country = countryRepository.create(new Country(resource.getName()));
        logger.info("Country with id " + country.getId() + " created");
        return MappingUtility.mapCountryToCountryDto(country);
    }


    public CountryDto update(CountryDto resource) {

        Country resourceToUpdate = RepositoryUtility.findIfExist(countryRepository, resource.getId(), RESOURCE_NAME);

        resourceToUpdate.setName(resource.getName());

        Country country = countryRepository.update(resourceToUpdate);
        logger.info("Country with id " + country.getId() + " updated");

        return MappingUtility.mapCountryToCountryDto(country);
    }


    public void deleteById(Long id) {

        RepositoryUtility.findIfExist(countryRepository, id, RESOURCE_NAME);

        countryRepository.deleteById(id);
        logger.info("Country with id " + id + " deleted");
    }
}
