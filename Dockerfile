# 1. Build stage
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn -DskipTests package

# 2. Run stage
FROM amazoncorretto:17-alpine3.18
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
