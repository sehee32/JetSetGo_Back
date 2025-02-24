package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.TbReservation;
import kr.co.jetsetgo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> createReservation(
            @RequestBody Map<String, Object> reservationData) throws Exception {
        TbReservation reservation = new TbReservation();
        List<Map<String, String>> flightData = (List<Map<String, String>>) reservationData.get("flightData");
        reservationService.insertReservation(reservation, flightData);

        return ResponseEntity.ok("Reservation 성공");
    }

    @PostMapping("/flights")
    public ResponseEntity<Map<String, Long>> saveFlights(@RequestBody List<Map<String, String>> flightData) {
        Long resultId = reservationService.saveFlights(flightData);
        return ResponseEntity.ok(Map.of("resultId", resultId));
    }
}
