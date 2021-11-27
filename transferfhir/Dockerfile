FROM openjdk:12-alpine as build
WORKDIR /workspace/app

RUN apk add --no-cache maven

COPY pom.xml .
COPY src src

RUN mvn clean install -DskipTests

FROM openjdk:12-alpine
ARG JAR_FILE=/workspace/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]