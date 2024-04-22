package com.onlineshopping.backend.service;

import com.onlineshopping.backend.model.Category;
import com.onlineshopping.backend.response.CategoryResponse;
import com.onlineshopping.backend.response.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(Category category);

    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);


    CategoryDTO updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);

}
