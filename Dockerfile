FROM openjdk:15-jdk-alpine as builder
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . .
RUN ./gradlew build -x test

FROM adoptopenjdk/openjdk15:jre-15.0.2_7-alpine as runner
COPY --from=builder /usr/src/app/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
