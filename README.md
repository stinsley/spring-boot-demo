A just for fun spring app to keep some skills in practice. 

Spring boot services demo affectionately known as "Wild fibonacci"
@author stinsley

REST API: 

    palindrome checker: 
    http://localhost:8080/palindrome?word=103
    
    Fibonacci Calculations: 
    localhost:8080/fibonacci?num=10 
    where 10 is whatever number you would like to pass in
    
    String Permutations: 
    localhost:8080/stringpermutations/?word=racecar
    
    prime number checker
    localhost:8080/prime?num=2

Service Health via Actuator: 

    localhost:8080/health
    
    This should return service health of {"status":"UP"}

metrics end points:

    //prometheus scrape page with all the metrics
    http://localhost:8080/prometheus/
    
    //see specific metric
    http://localhost:8080/metrics/Prime_Numerator
    
    //see available metrics
    http://localhost:8080/metrics
    

Run this locally in a container on kubernetes using helm && minikube: 
pkg requirements: 
helm-3.0+ (no tiller)
Kubernetes v1.18.2 
Docker 19.03.8
virtualbox/hyperkit/kvm(etc). - latest
java 11+ (using java 14 to be safe - this was coded on java 14)
gradle - latest

Note: to deploy this to MK you need to have configured .docker/config.json
Kubelet will merge any imagePullSecrets into a single virtual .docker/config.json
This is a typical configuration with private containers/repos. More info here: 
    https://kubernetes.io/docs/concepts/containers/images/#specifying-imagepullsecrets-on-a-pod
    https://docs.docker.com/registry/
    
"Running your own Registry is a great solution to integrate with and complement your CI/CD system.
In a typical workflow, a commit to your source revision control system would trigger a build on your CI system, 
which would then push a new image to your Registry if the build is successful. 
A notification from the Registry would then trigger a deployment on a staging environment, 
or notify other systems that a new image is available." - Docker

#STEPS TO RUN IN LOCAL MINIKUBE
1)
eval $(minikube docker-env)
docker run -d -p 5000:5000 --restart=always --name registry registry:2

2)use gradle to build the docker image (call the image name whatever you choose)
        $> docker build . -t demo

3) tag the image
(requires local docker repo container running and .docker/config.json configured)
    $> docker tag demo localhost:5000/demo
    #in AWS land you'd push to a repo ECR/EKR
    #locally helm can pull the image from the local docker repo, mk and kubernetes can as well
4) push the image to the local repo
    $> docker push localhost:5000/demo


5) ensure docker is running, start minikube telling it to use the local docker
    registry (--insecure-registry param)
    
    $> minikube start --insecure-registry
    or
    $> minikube start --driver=virtualbox --insecure-registry
    or 
    $> minikube --memory=5000 --driver=virtualbox --insecure-registry

6) use kubectl to push the image into minikube
    $> kubectl create deployment demo --image=localhost:5000/demo -o=yaml > deployment.yaml

7) verify the service launched ok in MK
   $> kubectl get pods, grab pod name

8) port forward and play with the service
    $>    kubectl port-forward <pod name> -n default 8080:8080



Clean up: 
    'minikube delete' will delete the VM instance of everything you've pushed. 


#Run locally in your ide: 
import the project
refresh gradle deps
click the play button

