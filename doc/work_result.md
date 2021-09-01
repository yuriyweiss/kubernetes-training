# Финальная конфигурация приложений

## На локальной машине

  - запускаются Zookeeper и Kafka 
    - брокер 192.168.0.102:9093;
  - запускается Spring Cloud Server 
    - адрес для получения конфигов http://192.168.0.102:8888;
  - запускается spring-docker-producer
    - url для получения сообщений http://192.168.0.102:8086/hello;
    - url для prometheus http://192.168.0.102:8086/actuator/prometheus;
  - запускается spring-docker-consumer
    - url для prometheus http://192.168.0.102:8087/actuator/prometheus;
  - запускаются Prometheus и Graphana
    - консоль Prometheus http://localhost:9095;
    - Graphana http://localhost:3000.
    
## В кластере Kubernetes

  - установить Prometheus
    - порт 9090;
  - установить spring-docker-loader
    - url для prometheus http://<dynamic_ip>:8085/actuator/prometheus.
    
# Тестирование приложений

На генерируюмую нагрузку влияет настройка loader: messages.loader.generate.rate (сообщений в секунду).

Каждая пода начинает генерировать такое количество сообщений в секунду.

Путём увеличения/уменьшения количества под достигается желаемый уровень нагрузки.