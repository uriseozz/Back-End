package com.asdf.ho.repository;

import com.asdf.ho.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryname(String categoryname);
}
