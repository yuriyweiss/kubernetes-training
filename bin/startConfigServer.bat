@echo off

set CURRENT_VERSION=%1
cd C:\Users\yuriy\work\personal-projects\kubernetes-training\spring-cloud-config-server\target
java -Dspring.profiles.active=native -jar spring-cloud-config-server-%CURRENT_VERSION%-SNAPSHOT.jar