package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class JeuDonnees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long producteurId;
    private String description;
    private String format;
    private LocalDate datePublication;

    @OneToMany(mappedBy = "jeuDonnees", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Metadonnee> metadonnees;

    // Méthode helper pour établir la relation bidirectionnelle
    public void setMetadonnees(List<Metadonnee> metadonnees) {
        this.metadonnees = metadonnees;
        if (metadonnees != null) {
            for (Metadonnee meta : metadonnees) {
                meta.setJeuDonnees(this);
            }
        }
    }

    // Méthode helper pour ajouter une métadonnée
    public void addMetadonnee(Metadonnee metadonnee) {
        if (this.metadonnees == null) {
            this.metadonnees = new java.util.ArrayList<>();
        }
        this.metadonnees.add(metadonnee);
        metadonnee.setJeuDonnees(this);
    }
}