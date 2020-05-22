package com.tinsley.fibonacci.demo.services;

import org.springframework.stereotype.Component;

@Component
public class PrimeChecker {

    public void primeCheck(int num){
        //check if num is positive
        if(num > 0){
            //check for primeality
            if(num % 2 == 0){
                System.out.println("is a prime: " + num + " " + false);
            }
            else{
                System.out.println("is a prime: " + num + " " + true);

            }

        }
        else{
            System.out.println(" please enter a number > 0");
        }



    }

}

