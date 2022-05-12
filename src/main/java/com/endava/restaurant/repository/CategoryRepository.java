package com.endava.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.endava.restaurant.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsCategoryByName(String name);
}
