FROM gradle:jdk-21-and-22-alpine AS build
WORKDIR /platform-service/discovery-service
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle
RUN gradle dependencies --no-daemon
COPY src ./src
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
COPY --from=build ./platform-service/discovery-service/build/libs/*.jar discovery-service.jar
ENTRYPOINT ["java", "-jar", "discovery-service.jar"]
