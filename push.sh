#!/bin/bash
set -e
minikube start --insecure-registry "127.0.0.0/24"
#set mk to share docker env
eval $(minikube docker-env)

if [ ! "$(docker ps -q -f name='registry')" ]; then
    docker run -d -p 5000:5000 --restart=always --name registry registry:2
fi
#install docker local registry
#use gradle to build the docker image (call the image name whatever you choose)
docker build . -t demo
# tag the image
docker tag demo localhost:5000/demo
#push the image to the local repo
docker push localhost:5000/demo
#Deploy via kubectl
kubectl create deployment demo --image=localhost:5000/demo -o=yaml > deployment.yaml
#grab teh deployed pod name

sleep 1m
POD=$(kubectl get pod -l app=demo -o jsonpath="{.items[0].metadata.name}")
#AUTO PORT FORWARD

kubectl port-forward "$POD" -n default 8080:8080
