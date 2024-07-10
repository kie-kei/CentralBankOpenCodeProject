FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY central-bank-rest-src/target/central-bank-rest-src-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]