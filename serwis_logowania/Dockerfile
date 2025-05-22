# Etap budowania aplikacji backendowej Java przy użyciu Mavena
FROM maven AS builder

# Katalog roboczy
WORKDIR /build

# Skopiowanie całego projektu Maven (kod źródłowy + pom.xml)
COPY . .

# Budowanie aplikacji Java bez testów – generuje plik .jar w target/
RUN mvn clean package -DskipTests

# Drugi etap: uruchamianie aplikacji z gotowego JAR-a
FROM openjdk:17-jdk-slim

# Ustawienie katalogu roboczego
WORKDIR /app

# Tworzenie standardowego użytkownika do uruchamiania aplikacji (z powodów bezpieczeństwa)
RUN groupadd -r webservice &&  \
    useradd -r webservice -g webservice && \
    chown -R webservice:webservice /app

# Skopiowanie gotowego artefaktu JAR z etapu budowania
COPY --from=builder /build/target/*.jar app.jar

# Otworzenie portu 8081 (port backendu logowania)
EXPOSE 8081

# Uruchomienie aplikacji jako użytkownik standardowy
USER webservice

# Uruchomienie aplikacji Spring Boot
ENTRYPOINT ["java","-jar","app.jar"]