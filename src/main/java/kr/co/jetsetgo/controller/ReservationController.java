package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /*
     * 예약 내역 저장
     */
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody Map<String, Object> reservationData) throws Exception {    // 프론트에서 전송된 데이터 받음

        List<?> flightIdObjects = (List<?>) reservationData.get("flightIds");   //flightIds키로 데이터 꺼내고 리스트로 변환
        List<Long> flightIds = new ArrayList<>();

        for(Object id : flightIdObjects) {      //flightIdObjects에서 id 하나씩 꺼냄
            flightIds.add(Long.parseLong(id.toString()));
        }

        reservationService.insertReservation(reservationData, flightIds);

        return ResponseEntity.ok("Reservation 성공");
    }




    @PostMapping("/flights")
    public ResponseEntity<Map<String, List<Long>>> saveFlights(@RequestBody List<Map<String, String>> flightData) {
        List<Long> resultIds = reservationService.saveFlights(flightData);
        return ResponseEntity.ok(Map.of("flightIds", resultIds));
    }
}
