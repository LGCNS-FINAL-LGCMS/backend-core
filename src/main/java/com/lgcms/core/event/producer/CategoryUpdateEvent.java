package com.lgcms.core.event.producer;

import com.lgcms.core.common.kafka.dto.CategoryEvent;
import com.lgcms.core.common.kafka.dto.KafkaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryUpdateEvent {

    @Value("spring.application.name")
    private String applicationName;
    private final KafkaTemplate kafkaTemplate;

    public void CategoryCreated(CategoryEvent categoryEvent) {
        KafkaEvent kafkaEvent = KafkaEvent.builder()
                .eventId(applicationName + UUID.randomUUID().toString())
                .eventType("CATEGORY_CREATED")
                .eventTime(LocalDateTime.now().toString())
                .data(null)
                .build();
        kafkaTemplate.send("CATEGORY", kafkaEvent);
    }

    public void CategoryModified(CategoryEvent categoryEvent) {
        KafkaEvent kafkaEvent = KafkaEvent.builder()
                .eventId(applicationName + UUID.randomUUID().toString())
                .eventType("CATEGORY_MODIFIED")
                .eventTime(LocalDateTime.now().toString())
                .data(categoryEvent)
                .build();
        kafkaTemplate.send("CATEGORY",kafkaEvent);
    }
}
