package com.lgcms.core.controller;

import com.lgcms.core.common.BaseResponse;
import com.lgcms.core.dto.request.FaqCreateRequest;
import com.lgcms.core.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;


    @PostMapping("")
    public ResponseEntity<BaseResponse> registerFaq(@RequestBody FaqCreateRequest  faqCreateRequest){
        faqService.createFaq(faqCreateRequest);
        return ResponseEntity.ok(BaseResponse.ok(null));

    }
}
