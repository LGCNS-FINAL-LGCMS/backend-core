package com.lgcms.core.service;

import com.lgcms.core.domain.Faq;
import com.lgcms.core.dto.request.FaqCreateRequest;
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
    public void createFaq(FaqCreateRequest faqCreateRequest) {
        Faq faq = Faq.builder()
                .question(faqCreateRequest.question())
                .answer(faqCreateRequest.answer())
                .build();


    }
}
