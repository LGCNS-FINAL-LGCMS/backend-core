package com.lgcms.core.repository;

import com.lgcms.core.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllBYSubCategoryId(Long subCategoryId);
}
