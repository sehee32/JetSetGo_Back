package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.ApiUtil;
import kr.co.jetsetgo.model.ResponseDto;
import kr.co.jetsetgo.service.HelloWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class HelloWordController {

    @Autowired
    private HelloWordService helloWordService;

    /**
     * Sample Controller
     * 호출 예시 : http://localhost:8080/api/Hello?data=호출테스트
     * 결과 : 호출테스트 Hello Word
     * @param data
     * @return
     */
    @GetMapping(value = "/Hello")
    public String selectHelloWorld(@RequestParam String data) {

        return helloWordService.selectHelloWorld(data);
    }

    @GetMapping("/Hello2")
    public String helloWorld2() throws IOException {

        return ApiUtil.getToken();
    }

    @GetMapping(value = "/Hello3")
    public String HelloWorld3(@RequestParam String data2) {
        return helloWordService.HelloWorld3(data2);
    }

    @GetMapping(value = "/TestPage")
    public String Test(@RequestParam String test) {
        return helloWordService.Test(test);
    }

    @GetMapping("/HelloYuri")
    public String HelloYuri(@RequestParam String name){

        return helloWordService.helloName(name);
    }

}