package com.tinsley.demo.services;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class StringPermutation {

    private final JSONObject json = new JSONObject();
    private int executionNum =0;

    public Object perm(String storageVar, String stringOfInterest) {
        int z = stringOfInterest.length();
        if (z == 0) {
            executionNum++;
            System.out.println(storageVar);
            if(!storageVar.isEmpty())
                save(storageVar, executionNum);

        } else {
            for (int i = 0; i < z; i++) { //loop thru chars passed in each call

                //substring to the end of what you pass the method on re-invocation
                perm(storageVar + stringOfInterest.charAt(i),
                        stringOfInterest.substring(0, i) + stringOfInterest.substring(i + 1, z));
            }
        }

        return getJson();
    }


    private void save(String sVar, int exe){

        json.put(Integer.toString(exe), sVar);

    }

    public void clear(){
        json.clear();
        executionNum = 0;
    }

    private String getJson(){
        return json.toJSONString();
    }
}
