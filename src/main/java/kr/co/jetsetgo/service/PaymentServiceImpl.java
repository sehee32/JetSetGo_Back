package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    // 예약내역 업데이트
    public void updatePayment(Map<String, String> data) {
        paymentMapper.updateReservationFlghts(data.get("reservation_Id"));
    }

}
