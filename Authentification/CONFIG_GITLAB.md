# Configuration GitLab pour le Microservice Authentification

## Fichier à coller sur GitLab (Config Server)

Créez un fichier nommé : **`Authentification.properties`**

```properties
spring.application.name=Authentification

# Server Port
server.port=8085

# Eureka Client
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.instance.prefer-ip-address=true

# Database Configuration (XAMPP MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/auth_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
jwt.secret=VotreCleSuperSecreteDeMinimum256BitsIciPourLaSecuriteJWTEtAuthenticationMicroservice2024
jwt.expiration=86400000

# Actuator
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always

# Logging
logging.level.com.example.demo=DEBUG
logging.level.org.springframework.security=DEBUG
```

## Instructions

1. **Dans votre dépôt GitLab de configuration**, créez ou modifiez le fichier `Authentification.properties`
2. Copiez le contenu ci-dessus
3. Commitez et poussez sur GitLab
4. Le Config Server récupérera automatiquement ces configurations

## Structure des fichiers dans le projet

Le microservice Authentification a maintenant **UN SEUL** fichier de configuration :

```
Authentification/
└── src/
    └── main/
        └── resources/
            └── application.properties  ← Fichier unique de configuration
```

### Contenu de `application.properties` (local)

Ce fichier reste dans le projet et référence optionnellement le Config Server :

```properties
spring.application.name=Authentification
spring.config.import=optional:configserver:http://localhost:9999
```

## Ordre de priorité

1. **Config Server GitLab** (Authentification.properties) - Priorité haute
2. **application.properties local** - Valeurs par défaut si Config Server non disponible

## Avantages

✅ Un seul fichier `application.properties` dans le projet
✅ Configuration centralisée sur GitLab via Config Server
✅ Le mot "optional" permet au service de démarrer même si Config Server est indisponible
✅ Pas besoin de `bootstrap.properties`
