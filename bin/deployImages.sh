#!/bin/bash

cd ~/work/personal-projects/kubernetes-training/kubernetes

args=("$@")
CURRENT_VERSION=${args[0]}
echo currentVersion: $currentVersion

#echo Deploy producer to minikube
#cat spring-docker-producer.yml | sed "s/{{CURRENT_VERSION}}/$CURRENT_VERSION/g" | kubectl apply -f -
#echo -e '\n\n'

#echo Expose producer service
#kubectl expose deployment spring-docker-producer --type=LoadBalancer --port=8086
#echo -e '\n\n'

#echo Deploy consumer to minikube
#cat spring-docker-consumer.yml | sed "s/{{CURRENT_VERSION}}/$CURRENT_VERSION/g" | kubectl apply -f -
#echo -e '\n\n'

#echo Expose consumer service
#kubectl expose deployment spring-docker-consumer --type=LoadBalancer --port=8087
#echo -e '\n\n'

echo Deploy loader to minikube
cat spring-docker-loader.yml | sed "s/{{CURRENT_VERSION}}/$CURRENT_VERSION/g" | kubectl apply -f -
echo -e '\n\n'

echo Expose loader service
kubectl expose deployment spring-docker-loader --type=LoadBalancer --port=8085
echo -e '\n\n'