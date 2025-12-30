# Guide de Test - Microservice Authentification

## Étapes pour tester le microservice d'authentification

### 1. Démarrer les services nécessaires

1. **XAMPP** - Démarrer MySQL
2. **Eureka Server** (port 8761)
3. **Config Server** (port 9999)
4. **Microservice Authentification** (port 8085)
5. **Application Angular** (port 4200)

### 2. Accéder à l'application

Ouvrez votre navigateur et accédez à : **http://localhost:4200**

Vous serez redirigé automatiquement vers la page de connexion.

### 3. Tester avec les comptes par défaut

#### Option 1 : Compte Admin
- **Username:** `admin`
- **Password:** `admin123`
- **Rôle:** ADMIN

#### Option 2 : Compte Agriculteur
- **Username:** `agriculteur1`
- **Password:** `agri123`
- **Rôle:** AGRICULTEUR

#### Option 3 : Compte Acheteur
- **Username:** `acheteur1`
- **Password:** `acheteur123`
- **Rôle:** ACHETEUR

### 4. Tester l'inscription

1. Cliquez sur "S'inscrire" sur la page de connexion
2. Remplissez le formulaire avec :
   - Nom d'utilisateur (min 3 caractères)
   - Email valide
   - Mot de passe (min 6 caractères)
   - Confirmation du mot de passe
   - Sélection du rôle (AGRICULTEUR, ACHETEUR, ou ADMIN)
3. Cliquez sur "S'inscrire"

### 5. Fonctionnalités à tester

✅ **Connexion**
- Connexion réussie avec les bons identifiants
- Message d'erreur avec des identifiants invalides
- Redirection vers la liste des producteurs après connexion

✅ **Inscription**
- Création d'un nouveau compte
- Validation des champs (email, mot de passe, etc.)
- Messages d'erreur si l'utilisateur ou l'email existe déjà

✅ **Navigation**
- Le header s'affiche uniquement quand on est connecté
- Affichage du nom d'utilisateur et du rôle dans le header
- Accès aux différentes pages de l'application

✅ **Déconnexion**
- Clic sur le bouton "Déconnexion"
- Redirection vers la page de connexion
- Suppression du token et des données utilisateur

### 6. Vérifier la base de données

Dans phpMyAdmin (XAMPP), vérifiez :
- Base de données : `auth_db`
- Table : `users`
- Les utilisateurs créés avec leurs rôles et mots de passe chiffrés

### 7. Tester les endpoints API directement

Vous pouvez aussi tester avec Postman ou curl :

```bash
# Health check
curl http://localhost:8085/api/auth/health

# Login
curl -X POST http://localhost:8085/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

# Register
curl -X POST http://localhost:8085/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"test123","email":"test@example.com","role":"AGRICULTEUR"}'

# Get current user (avec le token)
curl http://localhost:8085/api/auth/me \
  -H "Authorization: Bearer VOTRE_TOKEN_ICI"
```

## Problèmes courants

### Erreur de connexion au microservice
- Vérifiez que le microservice tourne sur le port 8085
- Vérifiez que MySQL (XAMPP) est démarré
- Vérifiez que Eureka Server est accessible

### Angular ne peut pas se connecter
- Redémarrez le serveur Angular après avoir modifié `proxy.conf.json`
- Vérifiez les logs de la console du navigateur (F12)

### Base de données non créée
- Vérifiez que MySQL tourne
- La base `auth_db` devrait être créée automatiquement
- Vérifiez les credentials dans `application.properties`

## Notes

- Le token JWT expire après 24 heures
- Les mots de passe sont chiffrés avec BCrypt
- Le proxy Angular redirige `/api/auth` vers `http://localhost:8085`
