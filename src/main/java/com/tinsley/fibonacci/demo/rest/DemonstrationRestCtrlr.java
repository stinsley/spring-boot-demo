package com.tinsley.fibonacci.demo.rest;

import com.tinsley.fibonacci.demo.service.StringPerm;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemonstrationRestCtrlr {
    /**
     *
     * Fields
     *
     */
    private final StringPerm stringPerm;
    private int fibonacci1;
    private int fibonacci2;

    /**
     * Constructor for services / params
     * @param stringPerm
     */
    public DemonstrationRestCtrlr(StringPerm stringPerm) {
        this.stringPerm = stringPerm;
        this.stringPerm.clear();

    }

    @RequestMapping(value = "/fibonacci", method = RequestMethod.POST)
    public void fiboin(@RequestParam int num){

    }


    @RequestMapping(value = "/stringpermutations", method = RequestMethod.POST)
    public Object perms(@RequestParam String word) {
        Object result = this.stringPerm.perm("", word);
        this.stringPerm.clear();
        return result;
    }

}
