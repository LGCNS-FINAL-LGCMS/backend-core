package com.lgcms.core.repository;

import com.lgcms.core.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllBySubCategoryId(Long subCategoryId);

    @Modifying
    @Query(value = "DELETE FROM item WHERE category_id = :id", nativeQuery = true)
    void deleteAllByCategoryId(@Param("id") Long id);
}
