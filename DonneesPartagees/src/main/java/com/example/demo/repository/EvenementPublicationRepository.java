package com.example.demo.repository;

import com.example.demo.entity.EvenementPublication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementPublicationRepository
        extends JpaRepository<EvenementPublication, Long> {
}
