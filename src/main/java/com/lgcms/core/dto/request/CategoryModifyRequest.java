package com.lgcms.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModifyRequest {

    private String category;
    private Long categoryId;
    private String modifyCategory;
}
