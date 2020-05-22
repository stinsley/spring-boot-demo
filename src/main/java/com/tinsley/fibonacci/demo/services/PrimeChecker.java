package com.tinsley.fibonacci.demo.services;

import org.springframework.stereotype.Component;

@Component
public class PrimeChecker {

    public boolean primeCheck(int num) {
        //check if num is positive
        if (num > 0) {
            //check for primeality
            if (num % 2 == 0 || num == 0 || num == 1 && num != 2) {
                System.out.println("is a prime: " + num + " " + false);
                return false;

            } else {
                System.out.println("is a prime: " + num + " " + true);
                return true;
            }

        }
        else{
            System.out.println(" please enter a number > 0");
            return false;
        }


    }

}

