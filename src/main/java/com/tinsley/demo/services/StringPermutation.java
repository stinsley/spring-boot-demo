package com.tinsley.demo.services;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class StringPermutation {
    /**
     * members
     */
    private final JSONObject json = new JSONObject();
    private MeterRegistry meterRegistry;
    private Counter executionNum;
    private Timer stopWatch;
    private long start;
    private int id =0;


    /**
     * constructor
     * @param meterRegistry
     */
    @Autowired
    public StringPermutation(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        //register the counters
        this.executionNum = this.meterRegistry.counter("string.permutation.executions", "Numerator", "Executions");
        this.stopWatch = meterRegistry.timer("permutation.timer", "type", "ping");
    }

    public Object perm(String storageVar, String stringOfInterest) {
        this.start = System.currentTimeMillis();
        int z = stringOfInterest.length();
        if (z == 0) {
            this.executionNum.increment();
            this.id++;
            System.out.println(storageVar);
            if(!storageVar.isEmpty())
                save(storageVar, id);
                this.stopWatch.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);

        } else {
            for (int i = 0; i < z; i++) { //loop thru chars passed in each call

                //substring to the end of what you pass the method on re-invocation
                perm(storageVar + stringOfInterest.charAt(i),
                        stringOfInterest.substring(0, i) + stringOfInterest.substring(i + 1, z));
            }
        }

        return getJson();
    }

    /**
     *
     * helpers
     * @param sVar
     * @param exe
     */
    private void save(String sVar, int exe){

        json.put(Integer.toString(exe), sVar);

    }

    public void clear(){
        json.clear();
        this.executionNum.close();
        this.id = 0;
    }

    private String getJson(){
        return json.toJSONString();
    }
}
