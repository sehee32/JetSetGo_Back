package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.PaymentMapper;
import kr.co.jetsetgo.model.PortonePaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    // 결제내역 저장
    public void insertPayment(Map<String, String> data) {
        PortonePaymentDto portonePaymentDto = new PortonePaymentDto();
        // 포트원 저장
        portonePaymentDto.setPayId(Long.parseLong(data.get("payId")));
        portonePaymentDto.setResCode(data.get("resCode"));
        portonePaymentDto.setRecepContent(data.get("recepContent"));
        portonePaymentDto.setStartDate(Timestamp.valueOf(data.get("startDate")));
        portonePaymentDto.setEndDate(Timestamp.valueOf(data.get("endDate")));

        paymentMapper.addPortonePayment(portonePaymentDto);
    }

}
