package com.example.demo.controller;


import com.example.demo.entity.JeuDonnees;
import com.example.demo.service.JeuDonneesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/donnees")
@Slf4j
public class JeuDonneesController {

    private final JeuDonneesService service;
    private final ObjectMapper objectMapper;

    public JeuDonneesController(JeuDonneesService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> publier(
            @RequestParam("jeuDonnees") String jeuDonneesJson,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            log.info("Réception d'une demande de publication de jeu de données");
            
            // Désérialiser le JSON
            JeuDonnees jeuDonnees = objectMapper.readValue(jeuDonneesJson, JeuDonnees.class);
            
            // Traiter le fichier si présent
            if (file != null && !file.isEmpty()) {
                log.info("Fichier reçu: {} ({})", file.getOriginalFilename(), file.getContentType());
                jeuDonnees.setFileName(file.getOriginalFilename());
                jeuDonnees.setFileType(file.getContentType());
                jeuDonnees.setFileSize(file.getSize());
                jeuDonnees.setFileData(file.getBytes());
            }
            
            JeuDonnees result = service.publier(jeuDonnees);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Erreur lors de la publication", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public List<JeuDonnees> getAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        try {
            JeuDonnees jeuDonnees = service.findById(id);
            
            if (jeuDonnees == null || jeuDonnees.getFileData() == null) {
                return ResponseEntity.notFound().build();
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(jeuDonnees.getFileType()));
            headers.setContentDispositionFormData("attachment", jeuDonnees.getFileName());
            headers.setContentLength(jeuDonnees.getFileSize());
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(jeuDonnees.getFileData());
        } catch (Exception e) {
            log.error("Erreur lors du téléchargement du fichier", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}/preview")
    public ResponseEntity<byte[]> previewFile(@PathVariable Long id) {
        try {
            JeuDonnees jeuDonnees = service.findById(id);
            
            if (jeuDonnees == null || jeuDonnees.getFileData() == null) {
                return ResponseEntity.notFound().build();
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(jeuDonnees.getFileType()));
            headers.setContentLength(jeuDonnees.getFileSize());
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(jeuDonnees.getFileData());
        } catch (Exception e) {
            log.error("Erreur lors de la prévisualisation du fichier", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
