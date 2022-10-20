package com.iljungitjung.domain.category.repository;

import com.iljungitjung.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findById(int categoryId);
}
