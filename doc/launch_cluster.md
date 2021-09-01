# Порядок запуска

Консоль управления - GitBash.

## 1. Поднять версию приложения и собрать проект

- в Идее запустить плагин Мавена - versions:set, задать там версию
- запустить сборку проекта Мавеном
- исправить версию в файле bin/startApplications_102.bat
- затем передавать тег в скрипты bin/buildImages.sh и bin/deployImages.sh

## 2. Запуск всех приложений, необходимых для работы minikube

И Кафка и приложения producer + consumer запускаются в единственном числе на локальной машине и спокойно обрабатывают весь поток сообщений.

- !!! не требуется - на машине 192.168.0.101 запустить elastic и kibana
- cd ~/work/personal-projects/kubernetes-training/bin
- ./startApplications_102.bat
- запустить Docker Desktop

## 3. Собрать образы и отправить на DockerHub

- cd ~/work/personal-projects/kubernetes-training/bin
- ./buildImages.sh 0.0.12

## 4. Запустить minikube

- в отдельном командном окне
  - minikube start
  - minikube dashboard
- удалить старые установки из Dashboard

## 5. Установка в minikube

- cd ~/work/personal-projects/kubernetes-training/bin
- ./deployImages.sh 0.0.12
- подождать запуск под
- в отдельном командном окне
  - minikube tunnel

## 6. Запуск нагрузки

При настройках WebFlux в 1000 коннекций не удаётся достичь нагрузки 1000 сообщений в секунду.

Поэтому именно приложение loader было отправлено в Kubernetes. Настройка количества сообщений в секунду помещена в SpringCloudServer. А увеличение/уменьшение нагрузки происходит за счёт увеличения/уменьшения количества под.