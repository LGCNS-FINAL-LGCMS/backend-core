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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/core/category")
    public ResponseEntity createCategory(@RequestBody CategoryRequest categoryRequest){

        categoryService.createCategory(categoryRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.ok(null));
    }

    @PostMapping("/admin/core/category/list")
    public ResponseEntity createCategoryList(@RequestBody List<CategoryRequest> categoryRequests){
        List<CategoryResponse> categoryResponses = categoryService.pushCategoryList(categoryRequests);
        return ResponseEntity.ok(BaseResponse.ok(categoryResponses));
    }

    @GetMapping("/core/category/list")
    public ResponseEntity<BaseResponse<CategoryListResponse>> getCategoryList(){
       CategoryListResponse response =  categoryService.getCategoryList();
       return ResponseEntity.ok(BaseResponse.ok(response));
    }

    @GetMapping("/core/category") // 디폴트 대분류 조회
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> getCategory(){
        List<CategoryResponse> categoryResponses = categoryService.getCategory();
        return ResponseEntity.ok(BaseResponse.ok(categoryResponses));
    }

    @GetMapping("/core/category/sub/{id}")
    public ResponseEntity<BaseResponse<List<SubCategoryResponse>>> getSubCategory(@PathVariable("id") Long categoryId){
        List<SubCategoryResponse> subCategoryResponses = categoryService.getSubCategory(categoryId);
        return ResponseEntity.ok(BaseResponse.ok(subCategoryResponses));
    }

    @GetMapping("/core/category/item/{id}")
    public ResponseEntity<BaseResponse<List<ItemResponse>>> getItem(@PathVariable("id") Long subCategoryId){
        List<ItemResponse> itemResponses = categoryService.getItems(subCategoryId);

        return ResponseEntity.ok(BaseResponse.ok(itemResponses));
    }

    @DeleteMapping("/core/category/{id}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(BaseResponse.ok(null));
    }
}
