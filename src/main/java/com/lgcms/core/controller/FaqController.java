package com.lgcms.core.controller;

import com.lgcms.core.common.BaseResponse;
import com.lgcms.core.dto.request.FaqRequest;
import com.lgcms.core.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;


    @PostMapping("")
    public ResponseEntity<BaseResponse> registerFaq(@RequestBody FaqRequest faqCreateRequest){
        faqService.createFaq(faqCreateRequest);
        return ResponseEntity.ok(BaseResponse.ok(null));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateFaa(@PathVariable("id") Long faqId, @RequestBody FaqRequest faqRequest){
        faqService.updateFaq(faqId,faqRequest);
        return ResponseEntity.ok(BaseResponse.ok(null));
    }
}
