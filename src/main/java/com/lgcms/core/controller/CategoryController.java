package com.lgcms.core.controller;

import com.lgcms.core.common.BaseResponse;
import com.lgcms.core.dto.request.CategoryRequest;
import com.lgcms.core.dto.response.CategoryListResponse;
import com.lgcms.core.dto.response.CategoryResponse;
import com.lgcms.core.dto.response.ItemResponse;
import com.lgcms.core.dto.response.SubCategoryResponse;
import com.lgcms.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity createCategory(@RequestBody CategoryRequest categoryRequest){

        categoryService.createCategory(categoryRequest);

        return ResponseEntity.ok(BaseResponse.ok(null));
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse> getCategoryList(){
       CategoryListResponse response =  categoryService.getCategoryList();
       return ResponseEntity.ok(BaseResponse.ok(response));
    }

    @GetMapping("") // 디폴트 대분류 조회
    public ResponseEntity<BaseResponse> getCategory(){
        List<CategoryResponse> categoryResponses = categoryService.getCategory();
        return ResponseEntity.ok(BaseResponse.ok(categoryResponses));
    }

    @GetMapping("/sub/{id}")
    public ResponseEntity<BaseResponse> getSubCategory(@PathVariable("id") Long categoryId){
        List<SubCategoryResponse> subCategoryResponses = categoryService.getSubCategory(categoryId);
        return ResponseEntity.ok(BaseResponse.ok(subCategoryResponses));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<BaseResponse> getItem(@PathVariable("id") Long subCategoryId){
        List<ItemResponse> itemResponses = categoryService.getItems(subCategoryId);

        return ResponseEntity.ok(BaseResponse.ok(itemResponses));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(BaseResponse.ok(null));
    }
}
