package com.example.demo.events;

import lombok.Data;

@Data
public class PublicationEvent {

    private Long producteurId;
    private String nomProducteur;
    private String typeCulture;
    private String action = "NOUVEAU_DATASET";
}
