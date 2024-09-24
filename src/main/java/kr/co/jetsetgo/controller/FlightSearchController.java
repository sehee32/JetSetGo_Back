package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.AirportDTO;
import kr.co.jetsetgo.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
public class FlightSearchController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public Map<String, List<AirportDTO>> searchAirports(@RequestParam String keyword) {
        List<AirportDTO> airportDTOs = airportService.searchAirportsByName(keyword); // 서비스 호출

        // 결과를 Map 형태로 반환
        Map<String, List<AirportDTO>> response = new HashMap<>();
        response.put("airports", airportDTOs);
        return response;
    }

}
