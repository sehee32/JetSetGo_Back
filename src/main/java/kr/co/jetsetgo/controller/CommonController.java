package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.service.HelloWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 공통 Controller
 */
@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    private HelloWordService helloWordService;

    /**
     * Sample Controller
     * 호출 예시 : http://localhost:8080/api/Hello?data=호출테스트
     * 결과 : 호출테스트 Hello Word
     *
     * @param data
     * @return
     */
    @GetMapping(value = "/Hello")
    public String selectHelloWorld(@RequestParam String data) {

        return helloWordService.selectHelloWorld(data);
    }
}
