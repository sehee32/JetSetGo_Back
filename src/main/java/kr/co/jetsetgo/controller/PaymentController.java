package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public boolean signUp(@RequestBody Map<String, String> data) {
        try {
            paymentService.insertPayment(data);
        } catch (Exception e)
        {
            System.out.println("[ERROR] portone 결제 정보 저장 실패 :" + e.getMessage());
            return false;
        }
        return true;
    }

}
