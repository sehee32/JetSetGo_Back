package com.example.jetsetgo_back;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWord {

    @GetMapping("/Hello")
    public String helloWorld() {
        return "hello!";
    }
}