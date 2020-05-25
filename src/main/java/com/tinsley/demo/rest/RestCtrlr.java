package com.tinsley.demo.rest;

import com.tinsley.demo.services.PrimeChecker;
import com.tinsley.demo.services.StringPermutation;
import com.tinsley.demo.services.WildFibonacci;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCtrlr {
    /**
     * Fields
     */
    private final StringPermutation stringPermutation;
    private final WildFibonacci fs;
    private final PrimeChecker usdaPrime;
    private MeterRegistry meterRegistry;
    private Counter restRequestCount;

    /**
     * Constructor for services / params / micrometer
     *
     * @param stringPermutation
     */
    public RestCtrlr(
            StringPermutation stringPermutation
            , WildFibonacci fs, PrimeChecker usdaPrime, MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.usdaPrime = usdaPrime;
        this.stringPermutation = stringPermutation;
        this.stringPermutation.clear();
        this.fs = fs;
        this.restRequestCount = this.meterRegistry.counter("Rest.Request.Count", "Count of rest requests", "Count of requests");


    }

    @RequestMapping(value = "/prime", method = RequestMethod.GET)
    public Boolean prime(@RequestParam int num) {
        this.restRequestCount.increment();
        return usdaPrime.primeCheck(num);
    }


    @RequestMapping(value = "/fibonacci", method = RequestMethod.GET)
    public Object[] fibonacci(@RequestParam int num) {
        this.restRequestCount.increment();
        Object[] res = fs.calculate(num);
        fs.clearResults();
        return res;
    }


    @RequestMapping(value = "/stringpermutations", method = RequestMethod.GET)
    public Object perms(@RequestParam String word) {
        this.restRequestCount.increment();
        Object result = this.stringPermutation.perm("", word);
        //clean up the object in the permutation class
        this.stringPermutation.clear();
        return result;
    }

}
