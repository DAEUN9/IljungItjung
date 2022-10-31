FROM openjdk:11-jdk AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

VOLUME /tmp

FROM openjdk:11-jdk
COPY --from=builder build/libs/*.jar app.jar
COPY --from=builder ./src ./src

EXPOSE 8080

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=deploy", "/app.jar"]