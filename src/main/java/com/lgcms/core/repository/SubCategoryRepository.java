package com.lgcms.core.repository;

import com.lgcms.core.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findAllByCategoryId(Long categoryId);

    @Modifying
    @Query(value = "DELETE FROM sub_category WHERE category_id = :id", nativeQuery = true)
    void deleteAllByCategoryId(@Param("id") Long id);
}
