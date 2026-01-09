# 🌾 Agri Data Sharing Platform

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-17-red.svg)](https://angular.io/)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)

> **Plateforme de partage de données agricoles** - Architecture microservices avec Spring Cloud

---

## 📋 Table des matières

- [Vue d'ensemble](#-vue-densemble)
- [Architecture](#-architecture)
- [Technologies](#-technologies)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Démarrage](#-démarrage)
- [API Endpoints](#-api-endpoints)

---

## 🎯 Vue d'ensemble

**Agri Data Sharing** est une plateforme de gestion et de partage de données agricoles basée sur une architecture microservices. Elle permet aux producteurs agricoles de publier leurs données et aux acheteurs d'y accéder de manière sécurisée.

### ✨ Fonctionnalités principales

- 🔐 Authentification JWT sécurisée
- 🚀 Architecture microservices scalable
- 📊 Gestion des données agricoles
- 📁 Upload et gestion de fichiers
- 🔄 Communication événementielle avec RabbitMQ
- 🌐 Interface utilisateur Angular
- 📡 Service discovery avec Eureka
- ⚙️ Configuration centralisée via GitLab

---

## 🏗 Architecture

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
```

### Services

| Service | Port | Description |
|---------|------|-------------|
| **Config Server** | 9999 | Configuration centralisée (GitLab) |
| **Eureka Server** | 8761 | Service discovery |
| **Gateway** | 8080 | API Gateway |
| **Authentification** | 8085 | Auth JWT & gestion utilisateurs |
| **DonneesPartagees** | 8082 | Gestion des données agricoles |
| **Producteurs** | 8081 | Gestion des producteurs |
| **Frontend** | 4200 | Interface Angular |

---

## 🛠 Technologies

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Cloud** (Config, Eureka, Gateway)
- **Spring Security + JWT**
- **Spring Data JPA**
- **MySQL 8.0**
- **RabbitMQ**

### Frontend
- **Angular 17**
- **TypeScript**
- **Bootstrap / Angular Material**

---

## 📦 Prérequis

- **Java 17+**
- **Maven 3.8+**
- **Node.js 18+** et **npm**
- **MySQL 8.0**
- **RabbitMQ** (optionnel)

---

## 🚀 Installation

### 1. Cloner le projet

```bash
git clone https://github.com/DorraMn/agri-data-sharing.git
cd agri-data-sharing
```

### 2. Configurer MySQL

Créer les bases de données :

```sql
CREATE DATABASE auth_db;
CREATE DATABASE producteurs_db;
CREATE DATABASE donnees_partagees_db;
```

### 3. Installer les dépendances Frontend

```bash
cd Frontspring/frontspring-app
npm install
```

---

## ▶️ Démarrage

### Ordre de démarrage des services

1. **MySQL** - Base de données (doit être démarré)
2. **Config Server** (port 9999)
3. **Eureka Server** (port 8761)
4. **Gateway** (port 8080)
5. **Authentification** (port 8085)
6. **Producteurs** (port 8081)
7. **DonneesPartagees** (port 8082)
8. **Frontend Angular** (port 4200)

### Commandes de démarrage

```bash
# Config Server
cd ConfigServer && ./mvnw spring-boot:run

# Eureka Server
cd eureka-server && ./mvnw spring-boot:run

# Gateway
cd Gateway && ./mvnw spring-boot:run

# Authentification
cd Authentification && ./mvnw spring-boot:run

# Producteurs
cd Producteurs && ./mvnw spring-boot:run

# DonneesPartagees
cd DonneesPartagees && ./mvnw spring-boot:run

# Frontend
cd Frontspring/frontspring-app && ng serve
```

---

## 🔗 API Endpoints

### Authentification (`/api/auth`)

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/auth/register` | Inscription |
| POST | `/api/auth/login` | Connexion |
| GET | `/api/auth/me` | Profil utilisateur |

### Producteurs (`/api/producteurs`)

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/producteurs` | Liste des producteurs |
| GET | `/api/producteurs/{id}` | Détail producteur |
| POST | `/api/producteurs` | Créer producteur |
| PUT | `/api/producteurs/{id}` | Modifier producteur |
| DELETE | `/api/producteurs/{id}` | Supprimer producteur |

### Données Partagées (`/api/donnees`)

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/donnees` | Liste des données |
| GET | `/api/donnees/{id}` | Détail donnée |
| POST | `/api/donnees` | Créer donnée |
| POST | `/api/donnees/{id}/fichiers` | Upload fichier |
| DELETE | `/api/donnees/{id}` | Supprimer donnée |

---

## 🔧 Configuration

Les fichiers de configuration des services sont gérés via **Spring Cloud Config Server** et stockés sur GitLab.

### URLs importantes

| Service | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| Gateway | http://localhost:8080 |
| Eureka Dashboard | http://localhost:8761 |
| Config Server | http://localhost:9999 |

---

## 👥 Auteur

**Dorra Moumen**

---

## 📄 Licence

Ce projet est sous licence MIT.
