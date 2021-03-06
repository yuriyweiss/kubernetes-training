@echo off
rem запуск приложений на машине 192.168.0.102

cd C:\Users\yuriy\work\apps\kafka_2.12-2.5.0
start "Zookeeper" bin\windows\zookeeper-server-start.bat config\zookeeper.properties
pause
start "Kafka" bin\windows\kafka-server-start.bat config\server.properties
pause
cd C:\Users\yuriy\work\personal-projects\kubernetes-training\bin
start "SpringCloudConfig" startConfigServer.bat 0.0.12
pause
start "SpringDockerProducer" startProducer.bat 0.0.12
pause
start "SpringDockerConsumer" startConsumer.bat 0.0.12
pause
cd C:\Users\yuriy\work\apps\grafana-8.1.2\bin
start "Graphana" grafana-server.exe
pause
cd C:\Users\yuriy\work\apps\prometheus-2.29.2
start "Prometheus" prometheus.exe --config.file=prometheus.yml --web.listen-address=:9095
