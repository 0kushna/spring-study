package com.kt.spring_study.control;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloControl {
    @GetMapping("/")
    public String printHello() {
        return "Hello World";
    }
    
}
