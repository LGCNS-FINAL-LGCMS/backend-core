package com.lgcms.core.controller.Internal;

import com.lgcms.core.common.dto.BaseResponse;
import com.lgcms.core.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal/core/faq")
@RequiredArgsConstructor
public class InternalFaqController {

    private final FaqService faqService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getFaq(){
        return ResponseEntity.ok(BaseResponse.ok(
                faqService.getFaq()
        ));
    }
}
