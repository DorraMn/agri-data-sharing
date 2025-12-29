package com.example.demo.repository;

import com.example.demo.entity.JeuDonnees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JeuDonneesRepository extends JpaRepository<JeuDonnees, Long> {
    
    @Query("SELECT DISTINCT j FROM JeuDonnees j LEFT JOIN FETCH j.metadonnees")
    List<JeuDonnees> findAllWithMetadonnees();
}