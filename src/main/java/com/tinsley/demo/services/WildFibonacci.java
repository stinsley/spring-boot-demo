package com.tinsley.demo.services;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WildFibonacci {


    List<Object> resultList = new ArrayList<>();

    public Object[] calculate(int upperLimit) {
        //calculate a fibonacci to a specified upper limit
        int result = 0;
        int sum = 0;
        int temp = 0;
        int temp1 = 1;
        resultList.clear();

        for (int x = 0; x < upperLimit; x++) {
            resultList.add(Integer.toString(sum));
            sum = temp1 + temp;
            temp = temp1;
            temp1 = sum;

        }

        return resultList.toArray();
    }

    /**
     *
     * helpers
     *
     */

    public void clearResults(){
        resultList.clear();
    }
}
