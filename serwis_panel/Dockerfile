FROM maven AS builder

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

RUN groupadd -r webservice && \
    useradd -r webservice -g webservice && \
    chown -R webservice:webservice /app

COPY --from=builder /build/target/*.jar app.jar

# Otworzenie portu 8082 (port drugiego serwisu)
EXPOSE 8082

USER webservice

ENTRYPOINT ["java","-jar","app.jar"]