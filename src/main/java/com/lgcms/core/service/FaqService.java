package com.lgcms.core.service;

import com.lgcms.core.common.exception.BaseException;
import com.lgcms.core.common.exception.FaqError;
import com.lgcms.core.domain.Faq;
import com.lgcms.core.dto.request.FaqRequest;
import com.lgcms.core.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FaqService {

    private final FaqRepository faqRepository;

    @Transactional
    public void createFaq(FaqRequest faqCreateRequest) {
        Faq faq = Faq.builder()
                .question(faqCreateRequest.question())
                .answer(faqCreateRequest.answer())
                .build();


    }
    @Transactional
    public void updateFaq(Long faqId,FaqRequest faqRequest) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(() -> new BaseException(FaqError.FAQ_NOT_FOUND));

        faq.modifyFaq(faqRequest.question(), faqRequest.answer());
    }
}
