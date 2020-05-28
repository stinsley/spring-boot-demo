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
vmware/virtualbox/hyperkit(etc). - latest
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
1)use gradle to build the docker image (call the image name whatever you choose)
    $> ./gradlew bootBuildImage --imageName=tinsley/demo-docker

2) use docker to push to your local docker repo (requires local docker repo container running and .docker/config.json configured)
    $> docker tag tinsley/demo-docker tinsley/demo-docker
    #in AWS land you'd push to a repo ECR/EKR
    
3) ensure docker is running, start minikube 
    $> minikube start
    or
    $> minikube start --driver=virtualbox
    or 
    $> minikube --memory=5000 --driver=virtualbox

4) use helm v3 to push app to k8 (put in any release name you would like)
    $> helm upgrade --install tinsley demo

5) verify the service launched in MK
   $> kubectl get pods

6) port forward and play with the service
    $>   export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=demo,app.kubernetes.io/instance=tinsley" -o jsonpath="{.items[0].metadata.name}")
         echo "Visit http://127.0.0.1:8080 to use your application"
         kubectl --namespace default port-forward $POD_NAME 8080:8090



Clean up: 
    'minikube delete' will delete the VM instance of everything you've pushed. 


#Run locally in your ide: 
import the project
refresh gradle deps
click the play button

