package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.service.HelloWordService;
import kr.co.jetsetgo.util.ApiUtil;
import kr.co.jetsetgo.util.ApiUtilTest;
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
     *
     * @param data
     * @return
     */
    @GetMapping(value = "/Hello")
    public String selectHelloWorld(@RequestParam String data) {

        return helloWordService.selectHelloWorld(data);
    }

    @GetMapping("/Hello2")
    public String helloWorld2() throws IOException {

        //파라미터
        String origin = "ICN"; // 출발지 공항 코드 (인천 국제공항)
        String destination = "JFK"; // 도착지 공항 코드 (JFK 국제공항)
        String departureDate = "2024-09-01"; // 출발 날짜
        int adults = 1; // 성인 승객 수
        int children = 0; // 어린이 승객 수
        String travelClass = "ECONOMY"; // 여행 클래스
        boolean nonStop = true; // 직항 여부

        return ApiUtil.searchFlights(origin, destination, departureDate, adults, children, travelClass, nonStop);
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
    public String HelloYuri(@RequestParam String name) {

        return helloWordService.helloName(name);
    }


}