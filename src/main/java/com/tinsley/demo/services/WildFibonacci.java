package com.tinsley.demo.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class WildFibonacci {
    /**
     *
     * members & fields
     *
     */

    private final MeterRegistry meterRegistry;
    List<Object> resultList = new ArrayList<>();
    private Counter executionNum;

    /**
     * Constructor
     * @param meterRegistry
     */
    @Autowired
    public WildFibonacci(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        //register the counters
        this.executionNum = this.meterRegistry.counter("fibonacci.executions", "Fibonacci", "Executions");
    }


    public Object[] calculate(int upperLimit) {
        //calculate a fibonacci to a specified upper limit
        int result = 0;
        int sum = 0;
        int temp = 0;
        int temp1 = 1;
        //clean up from last time
        resultList.clear();

        //inc the metrics
        this.executionNum.increment();

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
