# Image de base JDK 17
FROM eclipse-temurin:17-jre-alpine

# Créer un utilisateur non-root pour la sécurité
RUN addgroup -S spring && adduser -S spring -G spring

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR depuis le dossier target
COPY target/*.jar app.jar

# Changer le propriétaire du fichier
RUN chown spring:spring app.jar

# Utiliser l'utilisateur non-root
USER spring:spring

# Exposer le port
EXPOSE 8082

# Définir les variables d'environnement par défaut
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Point d'entrée pour démarrer l'application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
