package com.lgcms.core.repository;

import com.lgcms.core.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllBYSubCategoryId(Long subCategoryId);

    void deleteAllByCategoryId(Set<Long> singleton);
}
