FROM ubuntu:latest AS build

WORKDIR /app

RUN apt-get update && apt-get install -y openjdk-17-jdk

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests=true

COPY ./target/*.jar app.jar

EXPOSE 8081

CMD ["java","-jar","app.jar"]