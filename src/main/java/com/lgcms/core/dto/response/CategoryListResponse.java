package com.lgcms.core.dto.response;

import java.util.List;

public record CategoryListResponse(List<CategoryResponse> categoryResponses,
                                   List<SubCategoryResponse> subCategoryResponses,List<ItemResponse> itemResponses) {
}
