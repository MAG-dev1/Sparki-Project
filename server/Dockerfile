# Etapa 1: Construcción del JAR con Maven y OpenJDK 22
FROM maven:3-eclipse-temurin-22-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn test
RUN mvn clean package -DskipTests

# Etapa 2: Imagen para ejecutar la aplicación con Java 22
FROM eclipse-temurin:22-jre  
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar  
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
