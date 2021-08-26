FROM adoptopenjdk/openjdk11:alpine-jre
ARG jarVersion
ARG JAR_FILE=./target/spring-docker-consumer-${jarVersion}-SNAPSHOT.jar
WORKDIR /opt/app-consumer
COPY ${JAR_FILE} app-consumer.jar
ENTRYPOINT ["java","-jar","app-consumer.jar"]