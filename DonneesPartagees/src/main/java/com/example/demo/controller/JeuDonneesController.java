package com.example.demo.controller;


import com.example.demo.entity.JeuDonnees;
import com.example.demo.service.JeuDonneesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donnees")
@Slf4j
public class JeuDonneesController {

    private final JeuDonneesService service;

    public JeuDonneesController(JeuDonneesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> publier(@RequestBody JeuDonnees jeuDonnees) {
        try {
            log.info("Réception d'une demande de publication de jeu de données");
            JeuDonnees result = service.publier(jeuDonnees);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            log.error("Erreur lors de la publication", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public List<JeuDonnees> getAll() {
        return service.findAll();
    }
}
