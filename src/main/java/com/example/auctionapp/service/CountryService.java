package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.dto.CategoryDto;
import com.example.auctionapp.dto.CountryDto;
import com.example.auctionapp.model.Category;
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
public class CountryService {

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
}
