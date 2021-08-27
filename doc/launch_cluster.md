# Порядок запуска

Консоль управления - GitBash.

## 1. Поднять версию приложения и собрать проект

- в Идее запустить плагин Мавена - versions:set, задать там версию
- запустить сборку проекта Мавеном
- исправить версию в файле bin/startApplications.bat
- затем передавать тег в скрипты bin/buildImages.sh и bin/deployImages.sh

## 2. Запуск всех приложений, необходимых для работы minikube

- cd ~/work/personal-projects/kubernetes-training/bin
- ./startApplications.bat
- запустить Docker Desktop

## 3. Собрать образы и отправить на DockerHub

- cd ~/work/personal-projects/kubernetes-training/bin
- ./buildImages.sh 0.0.9

## 4. Запустить minikube

- в отдельном командном окне
  - minikube start
  - minikube dashboard
- удалить старые установки из Dashboard

## 5. Установка в minikube

- cd ~/work/personal-projects/kubernetes-training/bin
- ./deployImages.sh 0.0.9
- подождать запуск нод
- kubectl expose deployment spring-docker-producer --type=LoadBalancer --port=8080
- в отдельном командном окне
  - minikube tunnel 