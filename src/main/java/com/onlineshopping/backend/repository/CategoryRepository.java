package com.onlineshopping.backend.repository;

import com.onlineshopping.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//@RepositoryRestResource(collectionResourceRel = "category", path = "product-category")
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);

}
