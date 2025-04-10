FROM gradle:8.8-jdk17-alpine AS builder

WORKDIR /app
ENV GRADLE_USER_HOME=/home/gradle/.gradle

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
RUN ./gradlew dependencies --no-daemon

COPY src src
RUN ./gradlew clean bootJar --no-daemon -x test

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

ENV TZ=Asia/Seoul
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
