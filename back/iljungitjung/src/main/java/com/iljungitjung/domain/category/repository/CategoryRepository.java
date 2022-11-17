package com.iljungitjung.domain.category.repository;

import com.iljungitjung.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryNameAndUser_Email(String categoryName, String email);
    List<Category> findByUser_Id(Long id);
}
