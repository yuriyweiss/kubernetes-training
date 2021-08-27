@echo off
rem запуск всех приложений, необходимых для работы кластера minikube

cd C:\Users\yuriy\work\apps\kafka_2.12-2.5.0
start "Zookeeper" bin\windows\zookeeper-server-start.bat config\zookeeper.properties
pause
start "Kafka" bin\windows\kafka-server-start.bat config\server.properties
pause
cd C:\Users\yuriy\work\apps\elasticsearch-7.13.2
start "Elastic" bin\elasticsearch.bat
pause
cd C:\Users\yuriy\work\apps\kibana-7.13.2
start "Kibana" bin\kibana.bat
pause
cd C:\Users\yuriy\work\personal-projects\kubernetes-training\spring-cloud-config-server\target
start "SpringCloudConfig" java -Dspring.profiles.active=native -jar spring-cloud-config-server-0.0.9-SNAPSHOT.jar
