package com.lgcms.core.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InternalFaqResponse {

    private Long id;

    private String question;

    private String answer;

    private String imageUrl;

    private String url;
}
