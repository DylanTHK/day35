package com.spring.restAPI.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class ApiController {
    
    @Value("${test.value}")
    private String test;

    @Value("${inject.value}")
    private String inject;

    @GetMapping(path="/page")
    @ResponseBody
    public String getTest() {
        // System.out.println("in getTest()");
        System.out.println(">>> Test Value: " + test);

        System.out.println(">>> Injected Value: " + inject);

        return "testpage";
    }
}
