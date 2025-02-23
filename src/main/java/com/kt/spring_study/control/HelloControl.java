package com.kt.spring_study.control;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api") // 기본 경로로 지정함함
public class HelloControl {
    @GetMapping("/api") // /api 밑에 /api
    public String printHello() {
        return "Hello World";
    }
    
    @GetMapping("/hello") // api/hello
    public String Hello() {
        return "Hello!";
    }
}
