package com.example.jetsetgo_back;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class HelloWord {

    @GetMapping("/Hello")
    public String helloWorld() throws IOException {

        return "aa";
    }

    @GetMapping("/Hello2")
    public String helloWorld2() throws IOException {

        return ApiUtil.getToken();
    }
}

