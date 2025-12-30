package com.example.demo.service;

import com.example.demo.client.ProducteurClient;
import com.example.demo.client.ProducteurDTO;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ProducteurClient producteurClient;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Vérifier si le nom d'utilisateur existe déjà
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Ce nom d'utilisateur est déjà utilisé");
        }

        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        // Créer un nouvel utilisateur
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setRegion(request.getRegion());
        user.setIsActive(true);

        user = userRepository.save(user);

        // Si c'est un AGRICULTEUR, créer aussi un producteur dans la base producteurs
        if ("AGRICULTEUR".equalsIgnoreCase(request.getRole())) {
            try {
                ProducteurDTO producteur = new ProducteurDTO();
                producteur.setNom(request.getUsername());
                producteur.setEmail(request.getEmail());
                producteur.setRegion(request.getRegion() != null && !request.getRegion().isEmpty() 
                    ? request.getRegion() : "Non spécifiée");
                producteur.setRole(request.getRole());
                
                producteurClient.createProducteur(producteur);
            } catch (Exception e) {
                System.err.println("Erreur lors de la création du producteur: " + e.getMessage());
                // Continue même si la création du producteur échoue
            }
        }

        // Générer le token
        String token = tokenProvider.generateTokenFromUsername(user.getUsername());

        return new AuthResponse(token, user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return new AuthResponse(token, user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
}
