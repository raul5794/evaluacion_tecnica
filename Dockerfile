FROM eclipse-temurin:17
LABEL authors="Raul"
EXPOSE 8080
ARG JAR_FILE=targer/*.jar
COPY ./target/ventas-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]