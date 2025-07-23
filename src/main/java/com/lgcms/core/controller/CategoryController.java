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

}
