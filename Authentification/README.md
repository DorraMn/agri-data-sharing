# Authentification Microservice

Microservice d'authentification pour la plateforme de partage de données agricoles.

## Fonctionnalités

- Inscription des utilisateurs (AGRICULTEUR, ACHETEUR, ADMIN)
- Authentification par JWT
- Gestion des rôles
- Sécurité avec Spring Security

## Technologies

- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Tokens)
- Eureka Client
- Config Server Client

## Configuration

Le microservice se connecte à:
- **Eureka Server**: http://localhost:8761
- **Config Server**: http://localhost:9999
- **MySQL (XAMPP)**: localhost:3306/auth_db

## Endpoints

### Public
- `POST /api/auth/register` - Inscription
- `POST /api/auth/login` - Connexion
- `GET /api/auth/health` - Vérification de santé

### Protected (JWT requis)
- `GET /api/auth/me` - Informations de l'utilisateur connecté

## Port

Le service tourne sur le port **8085**

## Démarrage

```bash
mvn spring-boot:run
```

## Rôles disponibles

- **AGRICULTEUR**: Producteur agricole
- **ACHETEUR**: Acheteur de données
- **ADMIN**: Administrateur système
