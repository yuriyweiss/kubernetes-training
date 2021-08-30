@echo off
rem запуск приложений на машине 192.168.0.102

cd C:\Users\yuriy\work\apps\kafka_2.12-2.5.0
start "Zookeeper" bin\windows\zookeeper-server-start.bat config\zookeeper.properties
pause
start "Kafka" bin\windows\kafka-server-start.bat config\server.properties
pause
cd C:\Users\yuriy\work\personal-projects\kubernetes-training\spring-cloud-config-server\target
start "SpringCloudConfig" java -Dspring.profiles.active=native -jar spring-cloud-config-server-0.0.11-SNAPSHOT.jar
