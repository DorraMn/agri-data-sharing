package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    /**
     * Envoie un message JSON vers RabbitMQ
     */
    public void publish(Object payload) {

        rabbitTemplate.convertAndSend(
                exchangeName,
                routingKey,
                payload
        );

        log.info("📤 Message publié vers RabbitMQ (exchange={}, routingKey={}) => {}",
                exchangeName, routingKey, payload
        );
    }
}
