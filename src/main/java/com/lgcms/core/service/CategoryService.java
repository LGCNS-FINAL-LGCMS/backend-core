package com.lgcms.core.service;

import com.lgcms.core.domain.Category;
import com.lgcms.core.domain.Item;
import com.lgcms.core.domain.SubCategory;
import com.lgcms.core.dto.request.CategoryRequest;
import com.lgcms.core.dto.response.CategoryListResponse;
import com.lgcms.core.dto.response.CategoryResponse;
import com.lgcms.core.dto.response.ItemResponse;
import com.lgcms.core.dto.response.SubCategoryResponse;
import com.lgcms.core.repository.CategoryRepository;
import com.lgcms.core.repository.ItemRepository;
import com.lgcms.core.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.categoryName())
                .build();

        categoryRepository.save(category);

        SubCategory subCategory = SubCategory.builder()
                .categoryId(category.getId())
                .name(categoryRequest.subCategory())
                .build();

        subCategoryRepository.save(subCategory);

        Item item = Item.builder()
                .categoryId(category.getId())
                .subCategoryId(subCategory.getId())
                .name(categoryRequest.item())
                .build();

        itemRepository.save(item);

    }
}
