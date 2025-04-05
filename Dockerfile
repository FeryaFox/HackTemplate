FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /build

COPY . .

RUN mvn clean install -am -DskipTests


FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

CMD ["java", "-jar", "app.jar"]
