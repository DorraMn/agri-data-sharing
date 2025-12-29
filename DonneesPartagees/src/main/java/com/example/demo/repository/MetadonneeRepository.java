package com.example.demo.repository;


import com.example.demo.entity.Metadonnee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetadonneeRepository extends JpaRepository<Metadonnee, Long> {
}