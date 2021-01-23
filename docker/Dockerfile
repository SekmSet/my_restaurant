FROM maven:3.6.3-openjdk-15-slim as builder
WORKDIR /backend
COPY . .
#RUN mvn clean verify
ARG JAR_FILE=my_restaurant-0.0.1-SNAPSHOT.jar
RUN mvn clean install package -DskipTests

FROM openjdk:15-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=/backend/target/my_restaurant-0.0.1-SNAPSHOT.war
COPY --from=builder ${JAR_FILE} app.jar
COPY --from=builder /backend/public public
ENTRYPOINT ["java","-jar","/app.jar"]