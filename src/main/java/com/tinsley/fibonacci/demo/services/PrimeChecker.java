package com.tinsley.fibonacci.demo.services;

import org.springframework.stereotype.Component;

@Component
public class PrimeChecker {

    public void primeCheck(int num){
        //check if num is positive
        System.out.println("hit primecheck");

        if(num > 0){
            //check for primeality
            for(int x=2; x<num; x++){

                if(num % x == 0){
                    System.out.println("is a prime: " + num + " " + false);
                }
            }

            System.out.println("is a prime: " + num + " " + true); //number is prime
        }



    }

}

