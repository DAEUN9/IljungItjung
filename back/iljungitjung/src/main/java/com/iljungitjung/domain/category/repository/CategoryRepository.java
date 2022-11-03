package com.iljungitjung.domain.category.repository;

import com.iljungitjung.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryNameAndUser_Email(String categoryName, String email);
    Optional<Category> findById(Long id);
    Optional<Category> findByCategoryName(String categoryName);
}
