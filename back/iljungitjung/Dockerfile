FROM openjdk:11 as builder
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
COPY --from=builder ./src ./src

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar","-Dspring.profiles.active=deploy"]