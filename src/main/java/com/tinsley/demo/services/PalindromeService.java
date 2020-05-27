package com.tinsley.demo.services;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
public class PalindromeService {

    /**
     * members
     */
     private int index;
     private int palindrome;
     private MeterRegistry meterRegistry;
     private Timer stopWatch;
     private long start;


    /**
     * constructor
     */

    public PalindromeService(MeterRegistry meterRegistry){
        this.index = 0;
        this.meterRegistry = meterRegistry;
        this.stopWatch = meterRegistry.timer("palindrome.timer", "type", "ping");
        this.start = System.currentTimeMillis();
    }

    /**
     * Check palindrome
     * @param palindrome
     *
     * @return boolean
     */
    public Boolean checkPalindrome(Object palindrome){

        if(palindrome.equals(reverse(palindrome))){
            this.stopWatch.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
            return true;
        }

        return false;
    }

    /**
     * helpers
     */

    private Object reverse(Object palindrome){
        String workingString = palindrome.toString();
        String temp="";
        char[] charArray = workingString.toCharArray();
        for(int x = charArray.length-1; x>=0; x--){
                System.out.println(charArray[x]);
                temp += charArray[x];
                }

        return temp;
    }
}
