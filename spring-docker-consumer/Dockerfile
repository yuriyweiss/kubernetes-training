FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/spring-docker-consumer-0.0.6-SNAPSHOT.jar
WORKDIR /opt/app-consumer
COPY ${JAR_FILE} app-consumer.jar
ENTRYPOINT ["java","-jar","app-consumer.jar"]