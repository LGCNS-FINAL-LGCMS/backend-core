package com.lgcms.core.event.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@RequiredArgsConstructor
public class CategoryUpdateEvent {

    private final KafkaTemplate kafkaTemplate;

    public void CategoryUpdate(){

    }
}
