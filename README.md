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
    
