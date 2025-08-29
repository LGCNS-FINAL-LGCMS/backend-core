package com.lgcms.core.service;

import com.lgcms.core.common.dto.exception.BaseException;
import com.lgcms.core.common.dto.exception.FaqError;
import com.lgcms.core.domain.Faq;
import com.lgcms.core.dto.request.FaqRequest;
import com.lgcms.core.dto.response.FaqResponse;
import com.lgcms.core.dto.response.InternalFaqResponse;
import com.lgcms.core.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FaqService {

    private final FaqRepository faqRepository;

    @Transactional
    public void createFaq(FaqRequest faqCreateRequest) {
        Faq faq = Faq.builder()
                .url(faqCreateRequest.url())
                .imageUrl(faqCreateRequest.imageUrl())
                .question(faqCreateRequest.question())
                .answer(faqCreateRequest.answer())
                .build();

        faqRepository.save(faq);
    }
    @Transactional
    public void updateFaq(Long faqId,FaqRequest faqRequest) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(() -> new BaseException(FaqError.FAQ_NOT_FOUND));

        faq.modifyFaq(faqRequest.question(), faqRequest.answer(), faqRequest.imageUrl(), faqRequest.url());
    }

    @Transactional
    public void deleteFaq(Long faqId) {
        faqRepository.deleteById(faqId);
    }

    public List<FaqResponse> findAll() {
        List<FaqResponse> faqResponses = faqRepository.findAll()
                .stream()
                .map(faq -> new FaqResponse(faq.getId(),faq.getQuestion(),faq.getAnswer()))
                .toList();
        return faqResponses;
    }

    public List<InternalFaqResponse> getFaq() {
        List<Faq> faqList =  faqRepository.findAll();

        return faqList.stream()
                .map(faq -> InternalFaqResponse.builder()
                        .id(faq.getId())
                        .url(faq.getUrl())
                        .question(faq.getQuestion())
                        .answer(faq.getAnswer())
                        .imageUrl(faq.getImageUrl())
                        .url(faq.getUrl())
                        .build()).toList();
    }
}
