FROM gradle:jdk21-graal AS BUILD
WORKDIR /usr/app/
COPY . .
RUN ./gradlew fatJar --no-daemon
FROM openjdk:21-jdk-slim
COPY --from=BUILD /usr/app .
EXPOSE 8080
ENTRYPOINT exec java -jar build/libs/zalert-api-0.0.1-SNAPSHOT.jar