#!/bin/bash

cd ~/work/personal-projects/kubernetes-training/docker

args=("$@")
currentVersion=${args[0]}
echo currentVersion: $currentVersion

echo Build consumer image
docker build --build-arg jarVersion=$currentVersion -f consumer.dockerfile -t yuriyweiss/spring-docker-consumer:$currentVersion ../spring-docker-consumer
echo -e '\n\n'


echo Build producer image
docker build --build-arg jarVersion=$currentVersion -f producer.dockerfile -t yuriyweiss/spring-docker-producer:$currentVersion ../spring-docker-producer
echo -e '\n\n'

echo Push consumer image to DockerHub
docker push yuriyweiss/spring-docker-consumer:$currentVersion
echo -e '\n\n'

echo Push producer image to DockerHub
docker push yuriyweiss/spring-docker-producer:$currentVersion

