FROM adoptopenjdk/openjdk11:alpine-jre
ARG jarVersion
ARG JAR_FILE=./target/spring-docker-producer-${jarVersion}-SNAPSHOT.jar
WORKDIR /opt/app-producer
COPY ${JAR_FILE} app-producer.jar
ENTRYPOINT ["java","-jar","app-producer.jar"]