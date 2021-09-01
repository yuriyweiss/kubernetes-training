FROM adoptopenjdk/openjdk11:alpine-jre
ARG jarVersion
ARG JAR_FILE=./target/spring-docker-loader-${jarVersion}-SNAPSHOT.jar
WORKDIR /opt/app-loader
COPY ${JAR_FILE} app-loader.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","app-loader.jar"]