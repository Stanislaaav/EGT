package com.siliev.egt.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliev.egt.entities.StatisiticCollectorEntity;
import com.siliev.egt.services.EventService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.audit}")
    private String auditTopicName;

    public EventServiceImpl(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessageToBroker(StatisiticCollectorEntity event) {
        writeObjectAsString(event).ifPresent(msg -> {

            logger.info("Sending event to topic {}: {}", auditTopicName, event);
            kafkaTemplate.send(auditTopicName, msg).addCallback(
                stringStringSendResult -> logger.info("Event sent successfully"),
                throwable -> logger.warn("Event sending failed: ", throwable)
            );
        });
    }

    private Optional<String> writeObjectAsString(Object object) {
        try {
            return Optional.of(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            logger.warn("Error while writing event as string: ", e);
            return Optional.empty();
        }
    }
}

