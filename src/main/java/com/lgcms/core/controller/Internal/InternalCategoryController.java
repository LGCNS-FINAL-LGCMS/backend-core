package com.lgcms.core.controller.Internal;

import com.lgcms.core.common.BaseResponse;
import com.lgcms.core.dto.response.CategoryResponse;
import com.lgcms.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/internal/category")
@RequiredArgsConstructor
public class InternalCategoryController {

    private CategoryService categoryService;

    @GetMapping("")
    public List<CategoryResponse> memberCategory(){
       List<CategoryResponse> categoryResponses = categoryService.getInternalCategory();
    }
}
