# Порядок запуска

Консоль управления - GitBash.

## 1. Поднять версию приложения и собрать проект

- в Идее запустить плагин Мавена - versions:set, задать там версию
- запустить сборку проекта Мавеном
- исправить версию в файле bin/startApplications_102.bat
- затем передавать тег в скрипты bin/buildImages.sh и bin/deployImages.sh

## 2. Запуск всех приложений, необходимых для работы minikube

- на машине 192.168.0.101 запустить elastic и kibana
- cd ~/work/personal-projects/kubernetes-training/bin
- ./startApplications_102.bat
- запустить Docker Desktop

## 3. Собрать образы и отправить на DockerHub

- cd ~/work/personal-projects/kubernetes-training/bin
- ./buildImages.sh 0.0.11

## 4. Запустить minikube

- в отдельном командном окне
  - minikube start
  - minikube dashboard
- удалить старые установки из Dashboard

## 5. Установка в minikube

- cd ~/work/personal-projects/kubernetes-training/bin
- ./deployImages.sh 0.0.11
- подождать запуск нод
- kubectl expose deployment spring-docker-producer --type=LoadBalancer --port=8086
- kubectl expose deployment spring-docker-consumer --type=LoadBalancer --port=8087
- в отдельном командном окне
  - minikube tunnel

## 6. Запуск нагрузки

- на машине 192.168.0.102
- cd ~/work/personal-projects/kubernetes-training/spring-docker-loader/target
- java -jar spring-docker-loader-0.0.11-SNAPSHOT.jar 10 http://localhost:8086/hello/