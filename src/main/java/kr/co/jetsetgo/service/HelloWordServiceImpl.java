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
}
