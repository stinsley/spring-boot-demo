package com.tinsley.fibonacci.demo.rest;

import com.tinsley.fibonacci.demo.services.FibonacciService;
import com.tinsley.fibonacci.demo.services.StringPermutation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemonstrationRestCtrlr {
    /**
     * Fields
     */
    private final StringPermutation stringPermutation;
    private final FibonacciService fs;
    private int fibonacci1;
    private int fibonacci2;

    /**
     * Constructor for services / params
     *
     * @param stringPermutation
     */
    public DemonstrationRestCtrlr(StringPermutation stringPermutation, FibonacciService fs) {
        this.stringPermutation = stringPermutation;
        this.stringPermutation.clear();
        this.fs = fs;

    }

    @RequestMapping(value = "/fibonacci", method = RequestMethod.POST)
    public String fibonacci(@RequestParam int num) {
        return fs.calculate(num);
    }


    @RequestMapping(value = "/stringpermutations", method = RequestMethod.POST)
    public Object perms(@RequestParam String word) {
        Object result = this.stringPermutation.perm("", word);
        //clean up the object in the permutation class
        this.stringPermutation.clear();
        return result;
    }

}
