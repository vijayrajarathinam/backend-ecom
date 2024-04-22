package com.onlineshopping.backend.service.impl;


import com.onlineshopping.backend.exception.CategoryNotFoundException;
import com.onlineshopping.backend.model.Category;
import com.onlineshopping.backend.model.Product;
import com.onlineshopping.backend.repository.CategoryRepository;
import com.onlineshopping.backend.response.CategoryResponse;
import com.onlineshopping.backend.response.dto.CategoryDTO;
import com.onlineshopping.backend.service.CategoryService;
import com.onlineshopping.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());

        if (savedCategory != null) {
            throw new ResourceNotFoundException("Category with the name '" + category.getCategoryName() + "' already exists !!!");
        }

        savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortAndOrder  =
                sortOrder.equalsIgnoreCase("asc") ?
                        Sort.by(sortBy).ascending() :
                        Sort.by(sortBy).descending();
        Pageable pagedRequest = PageRequest.of(pageNumber,pageSize,sortAndOrder);
        Page<Category> pageDetails = categoryRepository.findAll(pagedRequest);
        List<Category> categories = pageDetails.getContent();

        if(categories.size() == 0)
            throw new CategoryNotFoundException("No categories created till now");

        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(pageDetails.getNumber());
        categoryResponse.setPageSize(pageDetails.getSize());
        categoryResponse.setTotalElements(pageDetails.getTotalElements());
        categoryResponse.setTotalPages(pageDetails.getTotalPages());
        categoryResponse.setLastPage(pageDetails.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO updateCategory(Category category, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Categories with id "+categoryId+ "not found"));

        category.setId(categoryId);
        savedCategory  = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category with categoryId"+ categoryId+ " not found"));

        List<Product> products = category.getProducts();

        products.forEach(product -> {
            productService.deleteProduct(product.getId());
        });

        categoryRepository.delete(category);

        return "Category with categoryId: " + categoryId + " deleted successfully !!!";

    }
}
