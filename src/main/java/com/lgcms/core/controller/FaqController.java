package com.lgcms.core.controller;

import com.lgcms.core.common.BaseResponse;
import com.lgcms.core.dto.request.FaqRequest;
import com.lgcms.core.dto.response.FaqResponse;
import com.lgcms.core.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;


    @PostMapping("")
    public ResponseEntity<BaseResponse> registerFaq(@RequestBody FaqRequest faqCreateRequest){
        faqService.createFaq(faqCreateRequest);
        return ResponseEntity.ok(BaseResponse.ok(null));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateFaq(@PathVariable("id") Long faqId, @RequestBody FaqRequest faqRequest){
        faqService.updateFaq(faqId,faqRequest);
        return ResponseEntity.ok(BaseResponse.ok(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteFaq(@PathVariable("id") Long faqId){
        faqService.deleteFaq(faqId);
        return ResponseEntity.ok(BaseResponse.ok(null));
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> getFaq(){
        List<FaqResponse> faqResponses = faqService.findAll();
        return ResponseEntity.ok(BaseResponse.ok(faqResponses));
    }
}
