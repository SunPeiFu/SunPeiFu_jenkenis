package com.sunpeifu.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("helloWorld")
    public String hello(){
        return "hello world";
    }
}
