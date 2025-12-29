package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageConsumer {

    private final EvenementPublicationService evenementService;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Consomme un message JSON RabbitMQ et stocke un EvenementPublication
     */
    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receive(String messageJson) {

        log.info("📩 Message reçu depuis RabbitMQ -> {}", messageJson);

        try {
            JsonNode json = mapper.readTree(messageJson);

            // Validation champs obligatoires
            if (json.get("producteurId") == null || json.get("typeCulture") == null) {
                log.error("⚠️ Message ignoré — champs requis manquants (producteurId / typeCulture)");
                return;
            }

            Long producteurId = json.get("producteurId").asLong();
            String typeCulture = json.get("typeCulture").asText();

            evenementService.enregistrerEvenement(
                    producteurId,
                    typeCulture,
                    messageJson
            );

            log.info(
                    "✅ EvenementPublication stocké (producteurId={}, typeCulture={})",
                    producteurId, typeCulture
            );

        } catch (Exception e) {
            log.error("❌ Erreur lors du traitement du message RabbitMQ", e);
        }
    }
}
