package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.ReservationMapper;
import kr.co.jetsetgo.model.TbReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;


    // 예약내역 저장
    public void insertReservation(TbReservation reservation) throws Exception {
        Map<String, String> data = new HashMap<String, String>();

        data.put("merchant_uid", "uid_" + String.valueOf(reservation.getMember_Id()) + reservation.getReservation_Date());
        data.put("amount", String.valueOf(reservation.getPayment_Amount()));
        data.put("currency", String.valueOf(reservation.getPayment_Method()));
        // 포트원 결제 금액 사전 등록 API 호출
        CommonServiceImpl.createOrderParam(data);

        reservationMapper.insertReservation(reservation);


    }
}
