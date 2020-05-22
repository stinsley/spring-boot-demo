package com.tinsley.fibonacci.demo.services;

import org.springframework.stereotype.Component;

@Component
public class WildFibonacci {


    public String calculate(int num) {

        double result = Math.pow(1 + Math.sqrt(num), num) - Math.pow(1 - Math.sqrt(num), num) / (Math.pow(num, 2));
        System.out.println(Math.pow(num, 2));

        return Double.toString(result);
    }
}
