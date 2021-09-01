@echo off

set CURRENT_VERSION=%1
cd C:\Users\yuriy\work\personal-projects\kubernetes-training\spring-docker-producer\target
java -Dspring.profiles.active=dev -jar spring-docker-producer-%CURRENT_VERSION%-SNAPSHOT.jar
