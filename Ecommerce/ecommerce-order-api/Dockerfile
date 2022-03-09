FROM maven:3.6.0-jdk-11-slim AS MAVEN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN ["mvn", "clean", "package", "-DskipTests"]
FROM adoptopenjdk:11-jre-hotspot
COPY --from=MAVEN /tmp/target/*.jar /tmp/target/ecommerce-order-api.jar
ENTRYPOINT ["java", "-jar", "/tmp/target/ecommerce-order-api.jar"]
