package com.example.auctionapp.service;

import com.example.auctionapp.Util.MappingUtility;
import com.example.auctionapp.dto.FilterDto.FilterCategoryDto;
import com.example.auctionapp.dto.FilterDto.FilterPriceDto;
import com.example.auctionapp.dto.FilterDto.FilterSubcategoryDto;
import com.example.auctionapp.dto.FilterDto.FiltersResponseDto;
import com.example.auctionapp.dto.ProductDto;
import com.example.auctionapp.dto.SearchRequest;
import com.example.auctionapp.enumeration.ColorEnum;
import com.example.auctionapp.enumeration.SizeEnum;
import com.example.auctionapp.model.Category;
import com.example.auctionapp.model.Subcategory;
import com.example.auctionapp.repository.BaseRepository;
import com.example.auctionapp.repository.CategoryRepository;
import com.example.auctionapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShopService {

    private final BaseRepository<Subcategory> subcategoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ShopService(BaseRepository<Subcategory> subcategoryRepository,
                       ProductRepository productRepository,
                       CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public FiltersResponseDto getShopFilters() {

        List<String> colors = ColorEnum.getColors();

        List<String> sizes = SizeEnum.getSizes();

        List<FilterCategoryDto> filterCategories = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();

        for(Category category : categories) {
            List<FilterSubcategoryDto> filterSubcategories = new ArrayList<>();
            for(Subcategory subcategory: category.getSubcategories()) {
                Long count = productRepository.getProductsCountBySubcategory(subcategory.getId());
                filterSubcategories.add(new FilterSubcategoryDto(subcategory.getId(), subcategory.getName(), count));
            }
            filterCategories.add(new FilterCategoryDto(category.getId(), category.getName(), filterSubcategories));
        }

        FilterPriceDto prices = productRepository.getPricesInfo();

        return new FiltersResponseDto(colors, sizes, filterCategories, prices);
    }

    public List<ProductDto> searchProducts(SearchRequest searchRequest) {

        return MappingUtility.mapProductListToDtoList(productRepository.getSearchProducts(searchRequest));
    }

}
