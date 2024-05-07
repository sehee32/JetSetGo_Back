package kr.co.jetsetgo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWordServiceImpl implements HelloWordService {

    /**
     * API Sample Service
     */
    public String selectHelloWorld(String data) {

        return data+"Hello World";
    }

    public String HelloWorld3(String data2) {
        return "연습연습연습";
    }

    public String Test(String test) {
        return test+"유정아사랑해^3^";
    }

    public String helloName(String name) {
        return "안녕하세요 " + name +" 님^^♡";
    }
}
