# 🌾 Agri Data Sharing Platform

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-21.0.0-red.svg)](https://angular.io/)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> **Plateforme moderne de partage de données agricoles** - Facilitez la collaboration entre producteurs et acheteurs avec une architecture microservices robuste.

---

## 📋 Table des matières

- [Vue d'ensemble](#-vue-densemble)
- [Architecture](#-architecture)
- [Fonctionnalités](#-fonctionnalités)
- [Technologies](#-technologies)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Démarrage](#-démarrage)
- [API Documentation](#-api-documentation)
- [Structure du projet](#-structure-du-projet)
- [Contribution](#-contribution)
- [Licence](#-licence)

---

## 🎯 Vue d'ensemble

**Agri Data Sharing** est une plateforme complète de gestion et de partage de données agricoles conçue avec une architecture microservices moderne. Elle permet aux producteurs agricoles de publier leurs données et aux acheteurs potentiels d'y accéder de manière sécurisée et efficace.

### ✨ Points forts

- 🔐 **Authentification JWT sécurisée**
- 🚀 **Architecture microservices scalable**
- 📊 **Gestion complète des données agricoles**
- 📁 **Upload et gestion de fichiers**
- 🔄 **Communication événementielle avec RabbitMQ**
- 🌐 **Interface utilisateur moderne avec Angular**
- 📡 **Service discovery avec Eureka**
- ⚙️ **Configuration centralisée**

---

## 🏗 Architecture

Notre plateforme utilise une architecture microservices moderne basée sur Spring Cloud :

```
┌─────────────────────────────────────────────────────────────┐
│                     Frontend (Angular)                       │
│                    http://localhost:4200                     │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────┐
│                   API Gateway (Port 8080)                    │
│              Routage & Load Balancing                        │
└────────┬─────────────┬──────────────┬──────────────┬────────┘
         │             │              │              │
         ▼             ▼              ▼              ▼
┌────────────┐ ┌──────────────┐ ┌──────────┐ ┌──────────────┐
│   Auth     │ │   Données    │ │  Prod.   │ │   Config     │
│   :8085    │ │   :8082      │ │  :8081   │ │   :9999      │
└─────┬──────┘ └──────┬───────┘ └────┬─────┘ └──────────────┘
      │               │              │
      └───────────────┴──────────────┘
                      │
                      ▼
         ┌────────────────────────┐
         │   Eureka Server        │
         │      :8761             │
         └────────────────────────┘
                      │
                      ▼
         ┌────────────────────────┐
         │   RabbitMQ             │
         │   Message Broker       │
         └────────────────────────┘
```

### Microservices

| Service | Port | Description |
|---------|------|-------------|
| **Config Server** | 9999 | Gestion centralisée des configurations |
| **Eureka Server** | 8761 | Service discovery et enregistrement |
| **Gateway** | 8080 | Point d'entrée unique, routage des requêtes |
| **Authentification** | 8085 | Gestion des utilisateurs et authentification JWT |
| **DonneesPartagees** | 8082 | Gestion des jeux de données agricoles |
| **Producteurs** | 8081 | Gestion des producteurs et exploitations |
| **Frontend** | 4200 | Interface utilisateur Angular |

---

## 🎨 Fonctionnalités

### 🔐 Authentification & Autorisation
- Inscription et connexion sécurisées
- Authentification JWT avec tokens
- Gestion des rôles (ADMIN, AGRICULTEUR, ACHETEUR)
- Protection des endpoints par rôle

### 👥 Gestion des utilisateurs
- Création de comptes multi-rôles
- Profils personnalisés par type d'utilisateur
- Gestion des exploitations agricoles
- Historique des activités

### 📊 Gestion des données
- Publication de jeux de données agricoles
- Upload de fichiers (CSV, Excel, PDF)
- Métadonnées enrichies
- Téléchargement et prévisualisation
- Recherche et filtrage avancés

### 🔔 Notifications & Événements
- Système de notifications en temps réel
- Événements de publication avec RabbitMQ
- Suivi des activités

### 📈 Monitoring & Observabilité
- Actuator endpoints pour le monitoring
- Dashboard Eureka pour la santé des services
- Logs structurés

---

## 🛠 Technologies

### Backend
- **Spring Boot 3.2.0** - Framework Java moderne
- **Spring Cloud** - Microservices ecosystem
  - Netflix Eureka - Service Discovery
  - Spring Cloud Config - Configuration centralisée
  - Spring Cloud Gateway - API Gateway
- **Spring Security** - Sécurité et authentification
- **Spring Data JPA** - Persistance des données
- **JWT (jjwt)** - Tokens d'authentification
- **RabbitMQ** - Message broker
- **MySQL** - Base de données relationnelle
- **Lombok** - Réduction du code boilerplate
- **Maven** - Gestion des dépendances

### Frontend
- **Angular 21.0.0** - Framework frontend moderne
- **TypeScript** - Langage typé
- **RxJS** - Programmation réactive
- **Angular Material** - Composants UI
- **Bootstrap** - Framework CSS

### DevOps & Tools
- **Git** - Contrôle de version
- **GitLab** - Repository de configuration
- **XAMPP** - Environnement de développement local
- **Postman** - Tests API

---

## 📦 Prérequis

Assurez-vous d'avoir les outils suivants installés :

- ☕ **Java JDK 17+** ([Télécharger](https://www.oracle.com/java/technologies/downloads/))
- 📦 **Maven 3.9+** ([Télécharger](https://maven.apache.org/download.cgi))
- 🗄️ **MySQL 8.0+** (via XAMPP ou standalone)
- 🟢 **Node.js 18+** et npm ([Télécharger](https://nodejs.org/))
- 🐰 **RabbitMQ** ([Télécharger](https://www.rabbitmq.com/download.html))
- 📝 **Git** ([Télécharger](https://git-scm.com/downloads))

---

## 🚀 Installation

### 1️⃣ Cloner le repository

```bash
git clone https://github.com/DorraMn/agri-data-sharing.git
cd agri-data-sharing
```

### 2️⃣ Configuration des bases de données

Démarrez **XAMPP** et créez les bases de données suivantes via phpMyAdmin :

```sql
CREATE DATABASE auth_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE producteurs_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE donnees_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3️⃣ Configuration RabbitMQ

Démarrez RabbitMQ et accédez au management console :
```
http://localhost:15672
Username: guest
Password: guest
```

### 4️⃣ Configuration du Config Server

Créez un repository GitLab avec les fichiers de configuration suivants :

**`Authentification.properties`**
```properties
server.port=8085
spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
spring.datasource.username=root
spring.datasource.password=
jwt.secret=VotreCleSuperSecreteDeMinimum256BitsIciPourLaSecuriteJWT
jwt.expiration=86400000
```

**`Producteurs.properties`**
```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/producteurs_db
spring.datasource.username=root
spring.datasource.password=
```

**`DonneesPartagees.properties`**
```properties
server.port=8082
spring.datasource.url=jdbc:mysql://localhost:3306/donnees_db
spring.datasource.username=root
spring.datasource.password=
```

**`Gateway.properties`**
```properties
server.port=8080
```

### 5️⃣ Installer les dépendances Frontend

```bash
cd Frontspring/frontspring-app
npm install
```

---

## ⚙️ Configuration

Mettez à jour le fichier de configuration du Config Server :

```properties
# ConfigServer/src/main/resources/application.properties
spring.cloud.config.server.git.uri=https://gitlab.com/VotreUsername/votre-repo-config.git
spring.cloud.config.server.git.username=votre-username
spring.cloud.config.server.git.password=votre-token
```

---

## 🎬 Démarrage

Démarrez les services dans l'ordre suivant :

### Backend Services

```bash
# 1. Config Server (Port 9999)
cd ConfigServer
./mvnw spring-boot:run

# 2. Eureka Server (Port 8761)
cd eureka-server
./mvnw spring-boot:run

# 3. Gateway (Port 8080)
cd Gateway
./mvnw spring-boot:run

# 4. Authentification (Port 8085)
cd Authentification
./mvnw spring-boot:run

# 5. Producteurs (Port 8081)
cd Producteurs
./mvnw spring-boot:run

# 6. DonneesPartagees (Port 8082)
cd DonneesPartagees
./mvnw spring-boot:run
```

### Frontend Application

```bash
cd Frontspring/frontspring-app
npm start
# L'application sera disponible sur http://localhost:4200
```

### 🔍 Vérification

Accédez aux URLs suivantes pour vérifier que tout fonctionne :

- 🌐 **Frontend** : http://localhost:4200
- 🔑 **Eureka Dashboard** : http://localhost:8761
- ⚙️ **Config Server** : http://localhost:9999/actuator/health
- 🚪 **Gateway** : http://localhost:8080/actuator/health

---

## 📚 API Documentation

### Authentification API (`/api/auth`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| POST | `/register` | Inscription d'un nouvel utilisateur | ❌ |
| POST | `/login` | Connexion et obtention du token JWT | ❌ |
| GET | `/me` | Récupération du profil utilisateur | ✅ |
| GET | `/health` | Health check du service | ❌ |

### Producteurs API (`/api/producteurs`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| GET | `/` | Liste tous les producteurs | ✅ |
| GET | `/{id}` | Détails d'un producteur | ✅ |
| POST | `/` | Créer un producteur | ✅ |
| PUT | `/{id}` | Modifier un producteur | ✅ |
| DELETE | `/{id}` | Supprimer un producteur | ✅ |

### Données Partagées API (`/api/donnees`)

| Méthode | Endpoint | Description | Auth |
|---------|----------|-------------|------|
| GET | `/` | Liste tous les jeux de données | ✅ |
| POST | `/` | Publier un jeu de données | ✅ |
| GET | `/{id}/download` | Télécharger un fichier | ✅ |
| GET | `/{id}/preview` | Prévisualiser un fichier | ✅ |

### 🔑 Authentification

Pour accéder aux endpoints protégés, incluez le token JWT dans le header :

```bash
Authorization: Bearer <votre-token-jwt>
```

### 📝 Exemple de requêtes

**Inscription**
```bash
curl -X POST http://localhost:8085/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "producteur1",
    "password": "password123",
    "email": "producteur@example.com",
    "role": "AGRICULTEUR",
    "region": "Île-de-France"
  }'
```

**Connexion**
```bash
curl -X POST http://localhost:8085/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "producteur1",
    "password": "password123"
  }'
```

**Publication de données**
```bash
curl -X POST http://localhost:8082/api/donnees \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: multipart/form-data" \
  -F "jeuDonnees={\"description\":\"Données de récolte\",\"format\":\"CSV\"}" \
  -F "file=@donnees.csv"
```

---

## 📁 Structure du projet

```
agri-data-sharing/
├── 📁 Authentification/          # Microservice d'authentification
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/.../
│   │   │   │   ├── controller/   # REST Controllers
│   │   │   │   ├── service/      # Logique métier
│   │   │   │   ├── repository/   # Accès aux données
│   │   │   │   ├── entity/       # Entités JPA
│   │   │   │   ├── security/     # Configuration sécurité
│   │   │   │   └── dto/          # Data Transfer Objects
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
│
├── 📁 ConfigServer/              # Serveur de configuration
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── 📁 eureka-server/             # Service Discovery
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── 📁 Gateway/                   # API Gateway
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
├── 📁 Producteurs/               # Microservice Producteurs
│   ├── src/main/java/.../
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── config/
│   └── pom.xml
│
├── 📁 DonneesPartagees/          # Microservice Données
│   ├── src/main/java/.../
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   └── client/              # Feign clients
│   └── pom.xml
│
├── 📁 Frontspring/               # Application Angular
│   └── frontspring-app/
│       ├── src/
│       │   ├── app/
│       │   │   ├── components/
│       │   │   ├── services/
│       │   │   ├── guards/
│       │   │   └── models/
│       │   ├── assets/
│       │   └── environments/
│       ├── package.json
│       └── angular.json
│
└── 📄 README.md                 # Ce fichier
```

---

## 👥 Rôles et permissions

| Rôle | Description | Permissions |
|------|-------------|-------------|
| 🔴 **ADMIN** | Administrateur système | Accès complet à toutes les fonctionnalités |
| 🟢 **AGRICULTEUR** | Producteur agricole | Publication et gestion de données, gestion des exploitations |
| 🔵 **ACHETEUR** | Acheteur de données | Consultation et téléchargement de données |

### Comptes par défaut

Après le premier démarrage, trois comptes sont créés automatiquement :

| Username | Password | Rôle |
|----------|----------|------|
| admin | admin123 | ADMIN |
| agriculteur1 | agri123 | AGRICULTEUR |
| acheteur1 | acheteur123 | ACHETEUR |

⚠️ **Important** : Changez ces mots de passe en production !

---

## 🧪 Tests

### Tests Backend

```bash
cd [microservice-name]
./mvnw test
```

### Tests Frontend

```bash
cd Frontspring/frontspring-app
npm test
```

### Tests d'intégration

```bash
# Tests end-to-end
npm run e2e
```

---

## 🐛 Dépannage

### Problème : Les services ne démarrent pas

**Solution** : Vérifiez que :
- MySQL est démarré (XAMPP)
- RabbitMQ est en cours d'exécution
- Les ports 8761, 8080, 8081, 8082, 8085, 9999 sont disponibles
- Les bases de données sont créées

### Problème : Erreur de connexion au Config Server

**Solution** :
- Vérifiez les credentials GitLab dans `ConfigServer/application.properties`
- Assurez-vous que le repository GitLab est accessible
- Vérifiez que le Config Server est démarré avant les autres services

### Problème : Frontend ne se connecte pas au backend

**Solution** :
- Vérifiez la configuration du proxy dans `proxy.conf.json`
- Redémarrez le serveur Angular après modification
- Vérifiez que la Gateway est accessible sur le port 8080

---

## 📈 Roadmap

- [ ] 🔍 Recherche avancée avec Elasticsearch
- [ ] 📊 Dashboard analytique avec visualisations
- [ ] 🔔 Notifications push en temps réel
- [ ] 📱 Application mobile (React Native)
- [ ] 🌍 Support multilingue
- [ ] 🔐 OAuth2 / Social login
- [ ] 📦 Conteneurisation avec Docker
- [ ] ☸️ Déploiement Kubernetes
- [ ] 🧪 Tests de charge et performance
- [ ] 📖 Documentation API avec Swagger

---

## 🤝 Contribution

Les contributions sont les bienvenues ! Voici comment contribuer :

1. 🍴 Forkez le projet
2. 🌿 Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. 💾 Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. 📤 Poussez vers la branche (`git push origin feature/AmazingFeature`)
5. 🔃 Ouvrez une Pull Request

### Standards de code

- ✅ Suivez les conventions Java standard
- ✅ Documentez le code avec Javadoc
- ✅ Écrivez des tests unitaires
- ✅ Respectez les principes SOLID
- ✅ Utilisez les features modernes de Java 17+

---

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

---

## 👨‍💻 Auteur

**Dorra Moumen**

- GitHub: [@DorraMn](https://github.com/DorraMn)
- Repository: [agri-data-sharing](https://github.com/DorraMn/agri-data-sharing)

---

## 🙏 Remerciements

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework backend
- [Angular](https://angular.io/) - Framework frontend
- [Netflix OSS](https://netflix.github.io/) - Microservices tools
- [RabbitMQ](https://www.rabbitmq.com/) - Message broker
- [MySQL](https://www.mysql.com/) - Base de données

---

## 📞 Support

Si vous rencontrez des problèmes ou avez des questions :

- 📧 Ouvrez une [Issue](https://github.com/DorraMn/agri-data-sharing/issues)
- 💬 Consultez la [Documentation](https://github.com/DorraMn/agri-data-sharing/wiki)
- ⭐ N'oubliez pas de mettre une étoile si ce projet vous a été utile !

---

<div align="center">

**Fait avec ❤️ pour la communauté agricole**

⭐️ Si ce projet vous a aidé, n'hésitez pas à lui donner une étoile !

</div>
