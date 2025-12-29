package com.example.demo.service;

import com.example.demo.entity.EvenementPublication;
import com.example.demo.repository.EvenementPublicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvenementPublicationService {

    private final EvenementPublicationRepository repository;

    public EvenementPublication enregistrerEvenement(
            Long producteurId,
            String typeCulture,
            String payloadJson
    ) {

        EvenementPublication evt = EvenementPublication.builder()
                .producteurId(producteurId)
                .typeCulture(typeCulture)
                .payloadJson(payloadJson)
                .dateReception(LocalDateTime.now())
                .build();

        EvenementPublication saved = repository.save(evt);

        log.info("✅ EvenementPublication stocké en base (id={})", saved.getId());

        return saved;
    }
}
