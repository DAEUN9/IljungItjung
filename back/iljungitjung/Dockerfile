FROM openjdk:11-jdk as builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

VOLUME /tmp

FROM openjdk:11
COPY --from=builder build/libs/*.jar app.jar

EXPOSE 9090

# 배포용 properties 실행 명령어
ENTRYPOINT ["java","-jar","/app.jar","--spring.config.name=application-prod"]

# Default properties 실행 명령어
# ENTRYPOINT ["java","-jar","app.jar"]