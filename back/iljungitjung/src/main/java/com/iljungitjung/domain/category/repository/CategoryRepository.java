package com.iljungitjung.domain.category.repository;

import com.iljungitjung.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUser_Nickname(String nickname);
    Optional<Category> findById(Long id);
    Optional<Category> findByCategoryName(String categoryName);
}
