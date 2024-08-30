package com.chung.jpapaging_demo.repository;

import com.chung.jpapaging_demo.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Transactional
    @Modifying
    @Query("delete  from Category c where c.id = :id")
    Integer deleteCategoryByStringId(@Param("id") String id);

    @Query("select c from Category c where c.id = :id")
    Category findCategoryById(@Param("id") String id);
}
