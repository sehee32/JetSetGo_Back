package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.util.ApiUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    @GetMapping("/search")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                @RequestParam String departureDate,
                                @RequestParam int adults,
                                @RequestParam int children,
                                @RequestParam String travelClass,
                                @RequestParam boolean nonStop) throws IOException {

        // API 호출 및 결과 반환
        return ApiUtil.searchFlights(origin, destination, departureDate, adults, children, travelClass, nonStop);
    }

    @GetMapping("/airports")
    public String searchAirports(@RequestParam String keyword) throws IOException {
        return ApiUtil.searchAirports(keyword);
    }
}
