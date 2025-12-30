package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si des utilisateurs existent déjà
        if (userRepository.count() == 0) {
            // Créer un utilisateur admin par défaut
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@example.com");
            admin.setRole("ADMIN");
            admin.setIsActive(true);
            userRepository.save(admin);

            // Créer un agriculteur par défaut
            User agriculteur = new User();
            agriculteur.setUsername("agriculteur1");
            agriculteur.setPassword(passwordEncoder.encode("agri123"));
            agriculteur.setEmail("agriculteur1@example.com");
            agriculteur.setRole("AGRICULTEUR");
            agriculteur.setIsActive(true);
            userRepository.save(agriculteur);

            // Créer un acheteur par défaut
            User acheteur = new User();
            acheteur.setUsername("acheteur1");
            acheteur.setPassword(passwordEncoder.encode("acheteur123"));
            acheteur.setEmail("acheteur1@example.com");
            acheteur.setRole("ACHETEUR");
            acheteur.setIsActive(true);
            userRepository.save(acheteur);

            System.out.println("Utilisateurs par défaut créés avec succès!");
        }
    }
}
