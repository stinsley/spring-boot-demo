package com.tinsley.fibonacci.demo.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class StringPerm {

    /**
     * Members
     */
    private int executionNum;
    private JSONObject json = new JSONObject();
    private String storageVar;
    private String stringOfInterest;



    public Object perm(String storageVar, String stringOfInterest) {
        int z = stringOfInterest.length();
        executionNum++;
        if (z == 0) {
            //print out final result/return final result
            //This stops the recursion from executing forever
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
    }

    private String getJson(){
        return json.toJSONString();
    }
}
