package com.example.demo.service;

import com.example.demo.client.ProducteurClient;
import com.example.demo.entity.JeuDonnees;
import com.example.demo.repository.JeuDonneesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class JeuDonneesService {

    private final JeuDonneesRepository repository;
    private final ProducteurClient producteurClient;

    public JeuDonneesService(JeuDonneesRepository repository,
                             ProducteurClient producteurClient) {
        this.repository = repository;
        this.producteurClient = producteurClient;
    }

    public JeuDonnees publier(JeuDonnees jeuDonnees) {
        log.info("Tentative de publication du jeu de données pour le producteur ID: {}", jeuDonnees.getProducteurId());

        try {
            // Vérification d'autorisation via microservice Producteurs
            boolean allowed = producteurClient.canPublish(jeuDonnees.getProducteurId());
            log.info("Autorisation de publication: {}", allowed);

            if (!allowed) {
                throw new RuntimeException("Accès refusé — Producteur non autorisé à publier des jeux de données");
            }

            // Configuration de la relation bidirectionnelle avec les métadonnées
            if (jeuDonnees.getMetadonnees() != null && !jeuDonnees.getMetadonnees().isEmpty()) {
                log.info("Configuration de {} métadonnées", jeuDonnees.getMetadonnees().size());
                // Force l'établissement de la relation bidirectionnelle
                jeuDonnees.getMetadonnees().forEach(meta -> {
                    log.info("Configuration métadonnée: {} = {}", meta.getCle(), meta.getValeur());
                    meta.setJeuDonnees(jeuDonnees);
                });
            } else {
                log.warn("Aucune métadonnée à configurer");
            }

            // Enregistrement si autorisé
            jeuDonnees.setDatePublication(LocalDate.now());
            JeuDonnees saved = repository.save(jeuDonnees);
            
            // Vérification après sauvegarde
            if (saved.getMetadonnees() != null) {
                log.info("Métadonnées sauvegardées: {}", saved.getMetadonnees().size());
                saved.getMetadonnees().forEach(meta -> {
                    log.info("Métadonnée sauvegardée - ID: {}, Clé: {}, JeuDonnees ID: {}", 
                        meta.getId(), meta.getCle(), meta.getJeuDonnees() != null ? meta.getJeuDonnees().getId() : "NULL");
                });
            }
            log.info("Jeu de données publié avec succès, ID: {}", saved.getId());
            return saved;
        } catch (Exception e) {
            log.error("Erreur lors de la publication du jeu de données", e);
            throw new RuntimeException("Erreur lors de la publication: " + e.getMessage(), e);
        }
    }

    public List<JeuDonnees> findAll() {
        log.info("Récupération de tous les jeux de données avec leurs métadonnées");
        List<JeuDonnees> jeux = repository.findAllWithMetadonnees();
        log.info("Nombre de jeux de données récupérés: {}", jeux.size());
        jeux.forEach(jeu -> {
            if (jeu.getMetadonnees() != null) {
                log.info("Jeu ID {}: {} métadonnées", jeu.getId(), jeu.getMetadonnees().size());
            }
        });
        return jeux;
    }
    
    public JeuDonnees findById(Long id) {
        log.info("Récupération du jeu de données ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jeu de données non trouvé avec l'ID: " + id));
    }
}
