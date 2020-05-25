package com.tinsley.demo.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrimeChecker {
    /**
     * Members
     *
     */
    private MeterRegistry meterRegistry;
    private Counter primeCounter;
    private Counter allRequests;
    /**
     * Constructor to bring in metrics
     * @param meterRegistry
     */
    @Autowired
    public PrimeChecker(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        //register the counters
        this.primeCounter = this.meterRegistry.counter("prime.numerator", "Numerator", "resultant_primes");
        this.allRequests = this.meterRegistry.counter("prime.denominator", "Denominator", "All_valid_requests");
    }

    public boolean primeCheck(int num) {
        //check if num is positive
        if (num > 0) {
            this.allRequests.increment();
            //check for primeality
            if (num % 2 == 0 && num != 2 || num == 0 || num == 1) {
                System.out.println("PRIME CLASS: is a prime: " + num + " " + false);
                return false;

            } else {
                System.out.println("PRIME CLASS: is a prime: " + num + " " + true);
                this.primeCounter.increment();

                return true;
            }

        }
        else{
            System.out.println(" please enter a number > 0");
            return false;
        }


    }

}

