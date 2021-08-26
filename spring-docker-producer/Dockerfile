FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=../target/spring-docker-producer-0.0.7-SNAPSHOT.jar
WORKDIR /opt/app-producer
COPY ${JAR_FILE} app-producer.jar
ENTRYPOINT ["java","-jar","app-producer.jar"]