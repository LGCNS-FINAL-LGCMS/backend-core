package com.lgcms.core.service;

import com.lgcms.core.common.dto.exception.BaseException;
import com.lgcms.core.common.dto.exception.CategoryError;
import com.lgcms.core.common.kafka.dto.CategoryEvent;
import com.lgcms.core.domain.Category;
import com.lgcms.core.domain.Item;
import com.lgcms.core.domain.SubCategory;
import com.lgcms.core.dto.request.CategoryModifyRequest;
import com.lgcms.core.dto.request.CategoryRequest;
import com.lgcms.core.dto.response.CategoryListResponse;
import com.lgcms.core.dto.response.CategoryResponse;
import com.lgcms.core.dto.response.ItemResponse;
import com.lgcms.core.dto.response.SubCategoryResponse;
import com.lgcms.core.event.producer.CategoryUpdateEvent;
import com.lgcms.core.repository.CategoryRepository;
import com.lgcms.core.repository.ItemRepository;
import com.lgcms.core.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ItemRepository itemRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CategoryUpdateEvent categoryUpdateEvent;

    @Transactional
    public void createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.categoryName())
                .build();

        categoryRepository.save(category);

        redisTemplate.opsForValue().set("CATEGORY:" + category.getId(), category.getName());
        CategoryEvent categoryEvent = CategoryEvent.builder()
                .categoryName(categoryRequest.categoryName())
                .key("CATEGORY:" + category.getId())
                .build();
        categoryUpdateEvent.CategoryCreated(categoryEvent);

    }


    public CategoryListResponse getCategoryList() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(category -> new CategoryResponse(category.getName(), category.getId()))
                .toList();

        List<SubCategory> subCategories = subCategoryRepository.findAll();
        List<SubCategoryResponse> subCategoryResponses = subCategories.stream()
                .map(subCategory -> new SubCategoryResponse(subCategory.getName(), subCategory.getId()))
                .toList();

        List<Item> items = itemRepository.findAll();
        List<ItemResponse> itemResponses = items.stream()
                .map(item -> new ItemResponse(item.getName(), item.getId()))
                .toList();
        return new CategoryListResponse(categoryResponses, subCategoryResponses, itemResponses);
    }

    @Transactional
    public List<CategoryResponse> getCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(category -> new CategoryResponse(category.getName(), category.getId()))
                .toList();
        return categoryResponses;
    }

    @Transactional
    public List<SubCategoryResponse> getSubCategory(Long categoryId) {
        List<SubCategory> subCategories = subCategoryRepository.findAllByCategoryId(categoryId);
        List<SubCategoryResponse> subCategoryResponses = subCategories.stream()
                .map(subCategory -> new SubCategoryResponse(subCategory.getName(), subCategory.getId()))
                .toList();

        return subCategoryResponses;
    }

    @Transactional
    public List<ItemResponse> getItems(Long subCategoryId) {
        List<Item> items = itemRepository.findAllBySubCategoryId(subCategoryId);
        List<ItemResponse> itemResponses = items.stream()
                .map(item -> new ItemResponse(item.getName(), item.getId()))
                .toList();

        return itemResponses;
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    public List<CategoryResponse> getInternalCategory() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(category.getName(), category.getId()))
                .toList();
    }

    @Transactional
    public List<CategoryResponse> pushCategoryList(List<CategoryRequest> categoryRequests) {

        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (CategoryRequest categoryRequest : categoryRequests) {
            System.out.println(categoryRequest.categoryName());
            Category category = Category.builder()
                    .name(categoryRequest.categoryName())
                    .build();
            categoryRepository.save(category);
            redisTemplate.opsForValue().set("CATEGORY:" + category.getId(), category.getName());
            categoryResponses.add(new CategoryResponse(category.getName(), category.getId()));
        }

        return categoryResponses;

    }

    @Transactional
    public String modifyCategory(CategoryModifyRequest categoryModifyRequest) {
        Category category = categoryRepository.findByName(categoryModifyRequest.getCategory());

        if (category == null) throw new BaseException(CategoryError.CATEGORY_NOT_FOUND);
        category.modifyCategory(categoryModifyRequest.getModifyCategory());
        String key = "CATEGORY:" + category.getId();

        redisTemplate.opsForValue().set(key, categoryModifyRequest.getModifyCategory());

        CategoryEvent categoryEvent = CategoryEvent.builder()
                .categoryName(categoryModifyRequest.getModifyCategory())
                .key(key)
                .build();
        categoryUpdateEvent.CategoryModified(categoryEvent);

        return categoryModifyRequest.getModifyCategory();
    }
}
