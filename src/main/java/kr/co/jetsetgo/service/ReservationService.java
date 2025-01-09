package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.ReservationMapper;
import kr.co.jetsetgo.model.TbReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;

    public void insertReservation(TbReservation reservation) {
        reservationMapper.insertReservation(reservation);
    }
}
