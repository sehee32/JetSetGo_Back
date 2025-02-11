package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.TbReservation;
import kr.co.jetsetgo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /*
    *   예약 내역 저장
    * */
    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody TbReservation reservation) throws Exception {
        reservationService.insertReservation(reservation);
        return ResponseEntity.ok("Reservation created successfully");
    }
}
