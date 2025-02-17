package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.dbio.MyPageMapper;
import kr.co.jetsetgo.model.TbFlights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FlightController {
    @Autowired
    private MyPageMapper myPageMapper;

    @PostMapping("/saveFlight")
    public ResponseEntity<Map<String, Object>> saveFlight(@RequestBody TbFlights flight) {
        try {
            // 기존 항공편 확인 후 저장
            long flightId = myPageMapper.checkAndAddFlight(flight);

            if (flightId == 0) {
                flightId = myPageMapper.findFlightId(flight);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("flightId", flightId);
            response.put("message", "항공편 저장 완료");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "항공편 저장 실패"));
        }
    }
}
