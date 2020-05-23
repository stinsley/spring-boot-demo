package com.tinsley.demo.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WildFibonacci {

    private final int result = 0;
    private int sum = 0;
    private int temp = 0;
    private int temp1 = 1;

    public Object[] calculate(int upperLimit) {
        //calculate a fibonacci to a specified upper limit
        List<Object> resultList = new ArrayList<>();
        resultList.clear();
        for (int x = 0; x < upperLimit; x++) {
            resultList.add(Integer.toString(sum));
            sum = temp1 + temp;
            temp = temp1;
            temp1 = sum;

        }
        return resultList.toArray();
    }
}
